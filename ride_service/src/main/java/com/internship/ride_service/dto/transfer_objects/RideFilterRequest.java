package com.internship.ride_service.dto.transfer_objects;

import com.internship.ride_service.enums.FareType;
import com.internship.ride_service.enums.OrderDirection;
import com.internship.ride_service.enums.RideFieldsToFilter;
import com.internship.ride_service.enums.RideStatus;

import java.time.LocalDate;

public record RideFilterRequest(
        LocalDate date,
        Long driverId,
        Long passengerId,
        RideStatus status,
        FareType fareType,
        int page,
        int size,
        RideFieldsToFilter sortBy,
        OrderDirection order
) {}