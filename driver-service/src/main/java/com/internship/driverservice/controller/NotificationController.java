package com.internship.driverservice.controller;

import com.internship.commonevents.event.ChangeRideStatusEvent;
import com.internship.driverservice.controller.doc.NotificationDoc;
import com.internship.driverservice.dto.response.PaymentByCashConfirmationDto;
import com.internship.driverservice.dto.response.RideCreatedNotificationDto;
import com.internship.driverservice.service.command.CommandNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController implements NotificationDoc {
    private final CommandNotificationService notificationService;

    @Override
    public RideCreatedNotificationDto updateRideCreationNotificationStatus(@PathVariable Long id, @RequestParam String status) {
        return notificationService.updateRideCreatedNotification(id, status);
    }

    @Override
    public PaymentByCashConfirmationDto confirmCashPayment(@PathVariable Long id, @RequestParam String status) {
        return notificationService.updatePaymentByCashNotification(id, status);
    }

    @Override
    public ChangeRideStatusEvent updateCurrentRideStatus(@PathVariable String rideId, @RequestParam String status) {
        return notificationService.updateRideStatus(rideId, status);
    }
}
