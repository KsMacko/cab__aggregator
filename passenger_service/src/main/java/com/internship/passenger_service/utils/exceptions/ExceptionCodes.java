package com.internship.passenger_service.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCodes {
    PASSENGER_NOT_FOUND("passenger.notFound"),
    PASSENGER_ID_IS_NULL("passenger.id.isNull"),
    RATE_NOT_FOUND("rate.notFound"),
    ERROR_INVALID_INPUT("error.input.invalid"),
    ERROR_NOT_READABLE("error.notReadable"),
    PHONE_ALREADY_EXISTS("phone.alreadyExists"),
    EMAIL_ALREADY_EXISTS("email.alreadyExists"),;
    private final String code;
}
