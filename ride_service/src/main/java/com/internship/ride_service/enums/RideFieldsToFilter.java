package com.internship.ride_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import static com.internship.ride_service.entity.Ride.Fields.*;

@Getter
@AllArgsConstructor
public enum RideFieldsToFilter {
    DATE(createdAt),
    START_TIME(startTime),
    END_TIME(endTime),
    DISTANCE(distance),
    RIDE_STATUS(status);
    private final String fieldName;
}
