package com.internship.passengerservice.utils.exceptions;

import java.util.List;

public record BaseValidationException(String message, List<String> errors) {
}
