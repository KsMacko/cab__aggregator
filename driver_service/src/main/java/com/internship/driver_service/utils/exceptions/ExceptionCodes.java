package com.internship.driver_service.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCodes {
    ERROR_NOT_READABLE("error.notReadable"),
    ERROR_INVALID_INPUT("error.invalid.input"),
    INVALID_PAGINATION("pagination.invalid"),
    DRIVER_NOT_FOUND("driver.notFound"),
    DRIVER_ID_NOT_NULL("driver.id.notNull"),
    DRIVER_PHONE_UNIQUE("driver.phone.unique"),
    RATE_NOT_FOUND("rate.notFound"),
    CAR_NOT_FOUND("car.notFound"),
    CAR_NUMBER_ALREADY_EXISTS("car.number.alreadyExists");
    private final String code;

}
