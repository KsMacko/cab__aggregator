package com.internship.ride_service.util.exceptions;

import java.util.List;

public record BaseValidationException(String message, List<String> errors) {
}
