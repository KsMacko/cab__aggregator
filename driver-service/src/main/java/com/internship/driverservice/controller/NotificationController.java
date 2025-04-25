package com.internship.driverservice.controller;

import com.internship.commonevents.event.ChangeRideStatusEvent;
import com.internship.driverservice.controller.doc.NotificationDoc;
import com.internship.driverservice.dto.mapper.NotificationMapper;
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

    private final NotificationMapper notificationMapper;
    private final CommandNotificationService notificationService;

    @Override
    public ResponseEntity<RideCreatedNotificationDto> updateRideCreationNotificationStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(
                notificationMapper.handleEntity(notificationService.updateRideCreatedNotification(id, status))
        );
    }

    @Override
    public ResponseEntity<PaymentByCashConfirmationDto> confirmCashPayment(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(
                notificationMapper.handleEntity(notificationService.updatePaymentByCashNotification(id, status))
        );
    }

    @Override
    public ResponseEntity<ChangeRideStatusEvent> updateCurrentRideStatus(@PathVariable String rideId,
                                                         @RequestParam String status) {
        return ResponseEntity.ok(notificationService.updateRideStatus(rideId, status));
    }
}
