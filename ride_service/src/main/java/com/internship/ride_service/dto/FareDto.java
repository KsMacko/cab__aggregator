package com.internship.ride_service.dto;

import com.internship.ride_service.enums.FareType;

import java.math.BigDecimal;


public record FareDto(
        FareType type,
        BigDecimal minPrice,
        Integer freeWaiting,
        BigDecimal paidWaitingPrice,
        BigDecimal pricePerKm,
        BigDecimal pricePerMin
) {
}