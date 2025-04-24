package com.internship.commonevents.event;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CashConfirmationRequest(
        String rideId,
        Long driverId,
        Long passengerId,
        BigDecimal amount
) {
}
