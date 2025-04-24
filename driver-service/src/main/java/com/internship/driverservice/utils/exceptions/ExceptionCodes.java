package com.internship.driverservice.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCodes {
    ERROR_NOT_READABLE("error.notReadable"),
    TYPE_MISMATCH("type.mismatch"),
    MISSING_PARAMETERS("missing.parameters"),
    ERROR_INVALID_INPUT("error.invalid.input"),
    UNKNOWN_ERROR("error.unknown"),
    INVALID_PAGINATION("pagination.invalid"),
    DRIVER_NOT_FOUND("driver.notFound"),
    DRIVER_ID_NOT_NULL("driver.id.notNull"),
    DRIVER_PHONE_UNIQUE("driver.phone.unique"),
    DRIVER_FOR_RIDE_NOT_FOUND("driver.forRide.notFound"),
    RATE_NOT_FOUND("rate.notFound"),
    CAR_NOT_FOUND("car.notFound"),
    CAR_NUMBER_ALREADY_EXISTS("car.number.alreadyExists"),
    PASSENGER_ID_NOT_MATCH_RIDE("passenger.id.notMatchRide"),
    DRIVER_ID_NOT_MATCH_RIDE("driver.id.notMatchRide"),
    RATE_AUTHOR_NOT_MATCH("rate.author.notMatch");
    private final String code;

}
