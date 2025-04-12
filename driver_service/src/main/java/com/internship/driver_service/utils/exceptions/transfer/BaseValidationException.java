package com.internship.driver_service.utils.exceptions.transfer;

import java.util.List;

public record BaseValidationException(String message, List<String> errors) {
}