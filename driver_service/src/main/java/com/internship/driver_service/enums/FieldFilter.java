package com.internship.driver_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldFilter {
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    CAR_NUMBER("carNumber"),
    FARE_TYPE("fareType"),
    DRIVER_STATUS("driverStatus"),
    PHONE("phone");
    private final String fieldName;

}