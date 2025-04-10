package com.internship.passenger_service.dto;

import lombok.Builder;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
@Builder
public record ProfileDto(
        Long profileId,
        String firstName,
        String email,
        String phone,
        Byte rate
) {
}
