package com.internship.ride_service.dto;

import com.internship.ride_service.enums.FareType;
import com.internship.ride_service.enums.RideStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.List;

public record RideDto(
        String id,
        String driverId,
        String passengerId,
        String promoCode,
        String startLocation,
        List<String> endLocation,
        OffsetDateTime createdAt,
        OffsetTime startWaitingTime,
        OffsetTime startTime,
        OffsetTime endTime,
        Float distance,
        RideStatus status,
        FareType fareType,
        BigDecimal price
) {
}
