package com.internship.ride_service.dto.response;

import java.time.OffsetDateTime;

public record ResponsePromoCodeDto(
        String promoCode,
        String id,
        Byte discount,
        String validUntil,
        OffsetDateTime createdAt
) {
}
