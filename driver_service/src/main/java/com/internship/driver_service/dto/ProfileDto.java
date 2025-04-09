package com.internship.driver_service.dto;

import com.internship.driver_service.enums.DriverStatus;
import com.internship.driver_service.enums.FareType;
import lombok.Builder;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
@Builder
public record ProfileDto(
        Long profileId,
        String firstName,
        String lastName,
        FareType fareType,
        DriverStatus driverStatus,
        String phone,
        Byte rate
) {}