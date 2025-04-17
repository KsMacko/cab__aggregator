package com.internship.driver_service.utils.exceptions;

import lombok.Getter;

@Getter
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
