package com.internship.driverservice.utils.exceptions;

import lombok.Getter;


@Getter
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
