package com.internship.rideservice.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionCodes {
    RIDE_NOT_FOUND("ride.notFound"),
    FARE_NOT_FOUND("fare.notFound"),
    FARE_ALREADY_EXISTS("fare.alreadyExists"),
    PROMO_CODE_NOT_FOUND("promo.code.notFound"),
    PROMO_CODE_STILL_VALID("promo.code.stillValid"),
    NO_CURRENT_PROMO_CODE("promo.code.noCurrentCode"),
    ERROR_INVALID_INPUT("error.input.invalid"),
    UNKNOWN_ERROR("error.unknown"),
    ERROR_NOT_READABLE("error.notReadable"),;
    private final String code;
}
