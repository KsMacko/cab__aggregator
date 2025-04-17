package com.internship.driver_service.utils.exceptions;

import lombok.Getter;


@Getter
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
