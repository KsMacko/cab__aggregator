package com.internship.driver_service.dto.transfer_objects;

import com.internship.driver_service.enums.DriverStatus;
import com.internship.driver_service.enums.FareType;
import com.internship.driver_service.enums.FieldFilter;
import com.internship.driver_service.enums.OrderDirection;


public record DriverFilterRequest(
        String phone,
        String carNumber,
        String firstName,
        String lastName,
        DriverStatus status,
        FareType fareType,
        Byte rate,
        int page,
        int size,
        FieldFilter sortBy,
        OrderDirection order
) {}