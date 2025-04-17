package com.internship.ride_service.dto.response;

import java.time.LocalDate;

public record ResponsePromoCodeDto(
        String promoCode,
        String id,
        Byte discount,
        String validUntil,
        LocalDate createdAt
) {
}
