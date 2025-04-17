package com.internship.ride_service.dto.response;

import java.time.LocalDateTime;

public record ResponsePromoCodeDto(
        String promoCode,
        String id,
        Byte discount,
        String validUntil,
        LocalDateTime createdAt
) {
}
