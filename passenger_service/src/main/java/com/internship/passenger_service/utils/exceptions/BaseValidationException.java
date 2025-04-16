package com.internship.passenger_service.utils.exceptions;

import java.util.List;

public record BaseValidationException(String message, List<String> errors) {
}
