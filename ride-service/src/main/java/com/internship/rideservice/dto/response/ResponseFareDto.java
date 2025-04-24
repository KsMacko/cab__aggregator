package com.internship.rideservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record ResponseFareDto(
        String type,
        BigDecimal minPrice,
        Integer freeWaiting,
        BigDecimal paidWaitingPrice,
        BigDecimal pricePerKm,
        BigDecimal pricePerMin,
        LocalDateTime createdAt
) {
}