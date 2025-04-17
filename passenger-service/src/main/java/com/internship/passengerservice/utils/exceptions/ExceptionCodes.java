package com.internship.passengerservice.utils.exceptions;

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
    EMAIL_ALREADY_EXISTS("email.alreadyExists"),
    TYPE_MISMATCH("type.mismatch"),
    UNKNOWN_ERROR("error.unknown");
    private final String code;
}
