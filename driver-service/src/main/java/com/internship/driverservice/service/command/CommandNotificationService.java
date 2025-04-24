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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

import static com.internship.driverservice.utils.validation.ValidationConstants.STATUS_IN_PROGRESS;
import static com.internship.driverservice.utils.validation.ValidationConstants.STATUS_WAIT_FOR_PASSENGER;

@Service
@RequiredArgsConstructor
public class CommandNotificationService {
    private final NotificationRepo notificationRepo;
    private final DriverProfileRepo driverProfileRepo;
    private final ArtemisProducer artemisProducer;
    private final ProfileValidationManager profileValidationManager;
    private final NotificationValidationManager notificationValidationManager;
    private final NotificationMapper notificationMapper;
    Logger logger = Logger.getLogger(CommandNotificationService.class.getName());

    @Transactional
    public RideCreatedNotificationDto updateRideCreatedNotification(Long notificationId, String status) {
        Notification notification = notificationValidationManager
                .checkNotificationAccordance(notificationId, NotificationType.RIDE_CREATION);
        notification.setStatus(NotificationStatus.valueOf(status));
        if(NotificationStatus.valueOf(status) == NotificationStatus.ACCEPTED) {
            notification.getDriverProfile().setDriverStatus(DriverStatus.DRIVING_TO_CLIENT);
           updateRideStatus(notification.getRideId(), status);
            List<Notification> notificationsForRide = notificationRepo.findByRideId(notification.getRideId());
            for (Notification otherNotification : notificationsForRide) {
                otherNotification.setActivity(NotificationActivity.NON_ACTIVE);
            }
        }
        notification.setActivity(NotificationActivity.NON_ACTIVE);
        RideCreationNotification rideCreation = notification.getRideCreationNotification();
        notificationRepo.save(notification);
        return notificationMapper.handleEntity(rideCreation);
    }

    @Transactional
    public PaymentByCashConfirmationDto updatePaymentByCashNotification(Long notificationId, String status) {
        Notification notification = notificationValidationManager
                .checkNotificationAccordance(notificationId, NotificationType.CASH_CONFIRMATION);
        PaymentByCashConfirmation confirmationNotification = notification.getPaymentByCashConfirmation();
        notification.setStatus(NotificationStatus.valueOf(status));
        notification.setActivity(NotificationActivity.NON_ACTIVE);
        notification.getDriverProfile().setDriverStatus(DriverStatus.FREE);
        artemisProducer.sendCashPaymentConfirmation(ConfirmedPaymentRequest.builder()
                        .passengerId(confirmationNotification.getPassengerId())
                        .amount(confirmationNotification.getAmount())
                        .driverId(notification.getDriverProfile().getProfileId())
                .build());
        return notificationMapper.handleEntity(confirmationNotification);
    }

    public void notifyAboutRideCreation(RideNotificationEvent event){
        List<DriverProfile> drivers = driverProfileRepo.findByDriverStatusAndFareType(
                DriverStatus.FREE,
                FareType.valueOf(event.fare())
        );
        for (DriverProfile driver : drivers) {
            logger.info("notified driver "+driver.getProfileId()+" about ride "+event.rideId()+" creation");
            Notification notification = Notification.builder()
                    .type(NotificationType.RIDE_CREATION)
                    .status(NotificationStatus.NON_VIEWED)
                    .rideId(event.rideId())
                    .activity(NotificationActivity.ACTIVE)
                    .driverProfile(driver)
                    .build();
            RideCreationNotification rideCreationNotification = RideCreationNotification.builder()
                    .notification(notification)
                    .startLocation(event.pickupLocation())
                    .endLocations(String.join(", ",event.endLocation()))
                    .build();
            notification.setRideCreationNotification(rideCreationNotification);
            notificationRepo.save(notification);
        }
    }

    public void notifyAboutRidePaymentByCashConfirmation(CashConfirmationRequest event){
        Notification notification = Notification.builder()
                .type(NotificationType.CASH_CONFIRMATION)
                .status(NotificationStatus.NON_VIEWED)
                .activity(NotificationActivity.ACTIVE)
                .driverProfile(profileValidationManager.getDriverProfile(event.driverId()))
                .rideId(event.rideId())
                .build();
        PaymentByCashConfirmation paymentConfirmation = PaymentByCashConfirmation.builder()
                .notification(notification)
                .passengerId(event.passengerId())
                .amount(event.amount())
                .build();
        notification.setPaymentByCashConfirmation(paymentConfirmation);
        logger.info("notified driver "+event.driverId()+" about ride payment");
        notificationRepo.save(notification);
    }

    public ChangeRideStatusEvent updateRideStatus(String rideId, String rideStatus) {
        DriverProfile driverProfile = profileValidationManager.findDriverByAcceptedRide(rideId);
        switch (rideStatus){
            case STATUS_WAIT_FOR_PASSENGER -> {
                driverProfile.setDriverStatus(DriverStatus.WAITING_FOR_CLIENT);
            }
            case STATUS_IN_PROGRESS -> {
                driverProfile.setDriverStatus(DriverStatus.IN_TRANSIT);
            }
        }
        ChangeRideStatusEvent event = ChangeRideStatusEvent.builder()
                .status(rideStatus)
                .driverId(driverProfile.getProfileId())
                .rideId(rideId)
                .build();
        artemisProducer.sendRideStatusUpdate(event);
        return event;
    }
}
