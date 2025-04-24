package com.internship.commonevents.event;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ConfirmedPaymentRequest(
        Long passengerId,
        BigDecimal amount,
        Long driverId
) {
}
