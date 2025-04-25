package com.internship.passengerservice.dto.response;

import lombok.Builder;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;

@FieldNameConstants
@Builder
public record ResponseProfileDto(
        Long profileId,
        String firstName,
        String email,
        String phone,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Integer rate
) {
}
