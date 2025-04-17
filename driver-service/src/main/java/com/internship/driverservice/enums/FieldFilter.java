package com.internship.driverservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.internship.driverservice.entity.Car.Fields.carNumber;
import static com.internship.driverservice.entity.DriverProfile.Fields.driverStatus;
import static com.internship.driverservice.entity.DriverProfile.Fields.fareType;
import static com.internship.driverservice.entity.DriverProfile.Fields.firstName;
import static com.internship.driverservice.entity.DriverProfile.Fields.lastName;
import static com.internship.driverservice.entity.DriverProfile.Fields.phone;

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