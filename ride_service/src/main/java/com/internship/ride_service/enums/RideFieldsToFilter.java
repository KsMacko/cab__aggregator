package com.internship.ride_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RideFieldsToFilter {
    DATE("date"),
    START_TIME("startTime"),
    END_TIME("endTime"),
    DISTANCE("distance"),
    RIDE_STATUS("status"),
    PRICE("price");
    private final String fieldName;
}
