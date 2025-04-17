package com.internship.rideservice.util.exceptions;

import java.util.List;

public record BaseValidationException(String message, List<String> errors) {
}
