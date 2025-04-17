package com.internship.rideservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.internship.rideservice.entity.Ride.Fields.createdAt;
import static com.internship.rideservice.entity.Ride.Fields.distance;
import static com.internship.rideservice.entity.Ride.Fields.endTime;
import static com.internship.rideservice.entity.Ride.Fields.startTime;
import static com.internship.rideservice.entity.Ride.Fields.status;

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
