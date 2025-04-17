package com.internship.passenger_service.dto.response;

import lombok.Builder;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@FieldNameConstants
@Builder
public record ResponseProfileDto(
        Long profileId,
        String firstName,
        String activatedPromoCodeId,
        String email,
        String phone,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Integer rate
) {
}
