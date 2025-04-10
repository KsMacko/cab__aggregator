package com.internship.ride_service.dto;

import java.time.OffsetDateTime;

public record PromoCodeDto (
        String promoCode,
        String id,
        Byte discount,
        OffsetDateTime validUntil,
        OffsetDateTime createdAt
){}
