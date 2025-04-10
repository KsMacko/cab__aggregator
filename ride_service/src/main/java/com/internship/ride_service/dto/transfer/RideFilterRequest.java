package com.internship.ride_service.dto.transfer;

import com.internship.ride_service.enums.FareType;
import com.internship.ride_service.enums.OrderDirection;
import com.internship.ride_service.enums.RideFieldsToFilter;
import com.internship.ride_service.enums.RideStatus;

import java.time.OffsetDateTime;

public record RideFilterRequest(
        OffsetDateTime createdAt,
        Long driverId,
        Long passengerId,
        RideStatus status,
        FareType fareType,
        int page,
        int size,
        RideFieldsToFilter sortBy,
        OrderDirection order
) {}