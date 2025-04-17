package com.internship.ride_service.dto.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ResponseFareDto(
        String type,
        BigDecimal minPrice,
        Integer freeWaiting,
        BigDecimal paidWaitingPrice,
        BigDecimal pricePerKm,
        BigDecimal pricePerMin,
        OffsetDateTime createdAt
) {
}