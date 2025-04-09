package com.internship.driver_service.dto;

import com.internship.driver_service.enums.DriverStatus;
import com.internship.driver_service.enums.FareType;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
public record ProfileDto(
        Long profileId,
        String firstName,
        String lastName,
        String carNumber,
        String carDescription,
        FareType fareType,
        DriverStatus driverStatus,
        String phone,
        Byte rate
) {}