package com.internship.driver_service.dto.response;

import lombok.experimental.FieldNameConstants;

@FieldNameConstants
public record ResponseProfileDto(
        Long profileId,
        String firstName,
        String lastName,
        String fareType,
        String driverStatus,
        String phone,
        Integer rate
) {
}