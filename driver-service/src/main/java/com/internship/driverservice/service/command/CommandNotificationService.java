package com.internship.driverservice.service.command;

import com.internship.commonevents.event.CashConfirmationRequest;
import com.internship.commonevents.event.ChangeRideStatusEvent;
import com.internship.commonevents.event.ConfirmedPaymentRequest;
import com.internship.commonevents.event.RideNotificationEvent;
import com.internship.driverservice.dto.mapper.NotificationMapper;
import com.internship.driverservice.dto.response.PaymentByCashConfirmationDto;
import com.internship.driverservice.dto.response.RideCreatedNotificationDto;
import com.internship.driverservice.entity.DriverProfile;
import com.internship.driverservice.entity.Notification;
import com.internship.driverservice.entity.PaymentByCashConfirmation;
import com.internship.driverservice.entity.RideCreationNotification;
import com.internship.driverservice.enums.DriverStatus;
import com.internship.driverservice.enums.FareType;
import com.internship.driverservice.enums.notification.NotificationActivity;
import com.internship.driverservice.enums.notification.NotificationStatus;
import com.internship.driverservice.enums.notification.NotificationType;
import com.internship.driverservice.repo.DriverProfileRepo;
import com.internship.driverservice.repo.NotificationRepo;
import com.internship.driverservice.service.communication.ArtemisProducer;
import com.internship.driverservice.utils.validation.NotificationValidationManager;
import com.internship.driverservice.utils.validation.ProfileValidationManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

import static com.internship.driverservice.utils.validation.ValidationConstants.STATUS_IN_PROGRESS;
import static com.internship.driverservice.utils.validation.ValidationConstants.STATUS_WAIT_FOR_PASSENGER;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandNotificationService {
    private final NotificationRepo notificationRepo;
    private final DriverProfileRepo driverProfileRepo;
    private final ArtemisProducer artemisProducer;
    private final ProfileValidationManager profileValidationManager;
    private final NotificationValidationManager notificationValidationManager;
    private final NotificationMapper notificationMapper;

    @Transactional
    public RideCreationNotification updateRideCreatedNotification(Long notificationId, String status) {
        Notification notification = notificationValidationManager
                .checkNotificationAccordance(notificationId, NotificationType.RIDE_CREATION);
        notification.setStatus(NotificationStatus.valueOf(status));
        if(NotificationStatus.valueOf(status) == NotificationStatus.ACCEPTED) {
            updateAllNotificationActivityAfterAccepting(notification, status);
        }
        notification.setActivity(NotificationActivity.NON_ACTIVE);
        RideCreationNotification rideCreation = notification.getRideCreationNotification();
        notificationRepo.save(notification);
        return rideCreation;
    }

    @Transactional
    public PaymentByCashConfirmation updatePaymentByCashNotification(Long notificationId, String status) {
        Notification notification = notificationValidationManager
                .checkNotificationAccordance(notificationId, NotificationType.CASH_CONFIRMATION);
        PaymentByCashConfirmation confirmationNotification = notification.getPaymentByCashConfirmation();
        notification.setStatus(NotificationStatus.valueOf(status));
        notification.setActivity(NotificationActivity.NON_ACTIVE);
        notification.getDriverProfile().setDriverStatus(DriverStatus.FREE);
        artemisProducer.sendCashPaymentConfirmation(
                buildConfirmedPaymentRequest(confirmationNotification, notification.getDriverProfile().getProfileId())
        );
        return confirmationNotification;
    }

    public void notifyAboutRideCreation(RideNotificationEvent event){
        List<DriverProfile> drivers = driverProfileRepo.findByDriverStatusAndFareType(
                DriverStatus.FREE,
                FareType.valueOf(event.fare())
        );
        for (DriverProfile driver : drivers) {
            log.info("notified driver {} about ride {} creation", driver.getProfileId(), event.rideId());
            Notification notification = buildNotificationFromRideCreationEvent(event, driver);
            RideCreationNotification rideCreationNotification = buildRideCreationNotification(notification, event);
            notification.setRideCreationNotification(rideCreationNotification);
            notificationRepo.save(notification);
        }
    }

    public void notifyAboutRidePaymentByCashConfirmation(CashConfirmationRequest event){
        Notification notification = buildNotificationFromCashConfirmationEvent(event);
        PaymentByCashConfirmation paymentConfirmation = buildPaymentByCashConfirmation(notification, event);
        notification.setPaymentByCashConfirmation(paymentConfirmation);
        log.info("notified driver {} about ride payment", event.driverId());
        notificationRepo.save(notification);
    }

    public ChangeRideStatusEvent updateRideStatus(String rideId, String rideStatus) {
        DriverProfile driverProfile = profileValidationManager.findDriverByAcceptedRide(rideId);
        if(rideStatus.equals(STATUS_WAIT_FOR_PASSENGER)){
            driverProfile.setDriverStatus(DriverStatus.WAITING_FOR_CLIENT);
        }
        else if(rideStatus.equals(STATUS_IN_PROGRESS)){
            driverProfile.setDriverStatus(DriverStatus.IN_TRANSIT);
        }
        ChangeRideStatusEvent event = buildChangeRideStatusEvent(rideId, rideStatus, driverProfile.getProfileId());
        artemisProducer.sendRideStatusUpdate(event);
        return event;
    }
    private Notification buildNotificationFromRideCreationEvent(RideNotificationEvent event, DriverProfile driver){
        return Notification.builder()
                .type(NotificationType.RIDE_CREATION)
                .status(NotificationStatus.NON_VIEWED)
                .rideId(event.rideId())
                .activity(NotificationActivity.ACTIVE)
                .driverProfile(driver)
                .build();
    }

    private Notification buildNotificationFromCashConfirmationEvent(CashConfirmationRequest event){
        return Notification.builder()
                .type(NotificationType.CASH_CONFIRMATION)
                .status(NotificationStatus.NON_VIEWED)
                .activity(NotificationActivity.ACTIVE)
                .driverProfile(profileValidationManager.getDriverProfile(event.driverId()))
                .rideId(event.rideId())
                .build();
    }

    private ChangeRideStatusEvent buildChangeRideStatusEvent(String rideId, String rideStatus, Long profileId){
        return ChangeRideStatusEvent.builder()
                .status(rideStatus)
                .driverId(profileId)
                .rideId(rideId)
                .build();
    }

    private ConfirmedPaymentRequest buildConfirmedPaymentRequest(PaymentByCashConfirmation confirmationNotification,
                                                                 Long profileId){
        return ConfirmedPaymentRequest.builder()
                .passengerId(confirmationNotification.getPassengerId())
                .amount(confirmationNotification.getAmount())
                .driverId(profileId)
                .build();
    }
    private RideCreationNotification buildRideCreationNotification(Notification notification,
                                                                   RideNotificationEvent event){
        return RideCreationNotification.builder()
                .notification(notification)
                .startLocation(event.pickupLocation())
                .endLocations(String.join(", ",event.endLocation()))
                .build();
    }

    private PaymentByCashConfirmation buildPaymentByCashConfirmation(Notification notification,
                                                                     CashConfirmationRequest event){
        return PaymentByCashConfirmation.builder()
                .notification(notification)
                .passengerId(event.passengerId())
                .amount(event.amount())
                .build();
    }

    private void updateAllNotificationActivityAfterAccepting(Notification notification, String status) {
        notification.getDriverProfile().setDriverStatus(DriverStatus.DRIVING_TO_CLIENT);
        updateRideStatus(notification.getRideId(), status);
        List<Notification> notificationsForRide = notificationRepo.findByRideId(notification.getRideId());
        for (Notification otherNotification : notificationsForRide) {
            otherNotification.setActivity(NotificationActivity.NON_ACTIVE);
        }
    }
}
