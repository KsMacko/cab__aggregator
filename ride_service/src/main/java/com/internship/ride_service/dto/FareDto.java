package com.internship.ride_service.dto;

import com.internship.ride_service.enums.FareType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record FareDto(
        FareType type,
        BigDecimal minPrice,
        Integer freeWaiting,
        BigDecimal paidWaitingPrice,
        BigDecimal pricePerKm,
        BigDecimal pricePerMin,
        OffsetDateTime createdAt
) {
}