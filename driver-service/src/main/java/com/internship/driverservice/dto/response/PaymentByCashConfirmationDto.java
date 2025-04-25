package com.internship.driverservice.dto.response;

import com.internship.driverservice.enums.notification.NotificationActivity;
import com.internship.driverservice.enums.notification.NotificationStatus;
import com.internship.driverservice.enums.notification.NotificationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentByCashConfirmationDto (
        Long id,
        String rideId,
        NotificationType type,
        NotificationStatus status,
        NotificationActivity activity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        BigDecimal amount,
        Long passengerId
){

}
