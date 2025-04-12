package com.internship.driver_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.internship.driver_service.entity.DriverProfile.Fields.driverStatus;
import static com.internship.driver_service.entity.DriverProfile.Fields.fareType;
import static com.internship.driver_service.entity.DriverProfile.Fields.firstName;
import static com.internship.driver_service.entity.DriverProfile.Fields.lastName;
import static com.internship.driver_service.entity.DriverProfile.Fields.phone;
import static com.internship.driver_service.entity.Car.Fields.carNumber;

@Getter
@AllArgsConstructor
public enum FieldFilter {
    FIRST_NAME(firstName),
    LAST_NAME(lastName),
    CAR_NUMBER(carNumber),
    FARE_TYPE(fareType),
    DRIVER_STATUS(driverStatus),
    PHONE(phone);
    private final String fieldName;

}