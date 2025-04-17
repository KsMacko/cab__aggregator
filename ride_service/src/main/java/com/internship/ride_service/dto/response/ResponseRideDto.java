package com.internship.ride_service.dto.response;

import com.internship.ride_service.enums.RideStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.List;

public record ResponseRideDto(
        String id,
        Long driverId,
        Long passengerId,
        String promoCode,
        String startLocation,
        List<String> endLocation,
        LocalDateTime createdAt,
        LocalTime startWaitingTime,
        LocalTime startTime,
        LocalTime endTime,
        Float distance,
        RideStatus status,
        String fareType,
        BigDecimal price
) {
}
