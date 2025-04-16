package com.internship.passenger_service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import static com.internship.passenger_service.utils.ValidationConstants.MAX_ID_VALUE;
import static com.internship.passenger_service.utils.ValidationConstants.MAX_RATE;
import static com.internship.passenger_service.utils.ValidationConstants.MIN_RATE;

public record RateDto(
        @Min(value = MIN_RATE, message = "rate.minValue")
        @Max(value = MAX_RATE, message = "rate.maxValue")
        @NotNull(message = "rate.notNull")
        Byte value,
        @Positive(message = "id.positive")
        @Max(value = MAX_ID_VALUE, message = "profile.id.maxValue")
        Long authorId,
        @Positive(message = "id.positive")
        @Max(value = MAX_ID_VALUE, message = "profile.id.maxValue")
        Long id) {
}
