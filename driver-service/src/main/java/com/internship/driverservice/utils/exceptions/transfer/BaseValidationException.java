package com.internship.driverservice.utils.exceptions.transfer;

import java.util.List;

public record BaseValidationException(String message, List<String> errors) {
}