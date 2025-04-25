package com.internship.driverservice.controller.doc;

import com.internship.commonevents.event.ChangeRideStatusEvent;
import com.internship.driverservice.dto.response.PaymentByCashConfirmationDto;
import com.internship.driverservice.dto.response.RideCreatedNotificationDto;
import com.internship.driverservice.utils.validation.ValidationConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.internship.driverservice.utils.validation.ValidationConstants.NOTIFICATION_STATUS;
import static com.internship.driverservice.utils.validation.ValidationConstants.RIDE_STATUS_PATTERN;

@RequestMapping("/api/v1/drivers")
@Validated
public interface NotificationDoc {
    @PostMapping("/notifications/ride-creation-notification/{id}/status")
    ResponseEntity<RideCreatedNotificationDto> updateRideCreationNotificationStatus(
            @PathVariable("id")
            @PositiveOrZero(message = "id.positive")
            @Max(value = ValidationConstants.MAX_ID_VALUE, message = "id.maxValue")
            Long id,
            @Pattern(regexp = NOTIFICATION_STATUS, message = "notification.status.invalidInput")
            @RequestParam String status);

    @PostMapping("/notifications/confirm-payment-notification/{id}/status")
    ResponseEntity<PaymentByCashConfirmationDto> confirmCashPayment(
            @PathVariable("id")
            @PositiveOrZero(message = "id.positive")
            @Max(value = ValidationConstants.MAX_ID_VALUE, message = "id.maxValue")
            Long id,
            @Pattern(regexp = NOTIFICATION_STATUS, message = "notification.status.invalidInput")
            @RequestParam String status);
    @PostMapping("/notifications/current-ride/{rideId}/status")
    ResponseEntity<ChangeRideStatusEvent> updateCurrentRideStatus(
            @NotBlank(message = "ride.id.notEmpty")
            @PathVariable
            String rideId,
            @Pattern(regexp = RIDE_STATUS_PATTERN, message = "notification.status.invalidInput")
            @RequestParam String status);

}
