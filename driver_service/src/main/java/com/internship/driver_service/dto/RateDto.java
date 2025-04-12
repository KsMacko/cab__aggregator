package com.internship.driver_service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;

import static com.internship.driver_service.utils.validation.ValidationConstants.MAX_ID_VALUE;
import static com.internship.driver_service.utils.validation.ValidationConstants.MAX_RATE;
import static com.internship.driver_service.utils.validation.ValidationConstants.MIN_RATE;

public record RateDto(
        @Min(value = MIN_RATE, message = "rate.minValue")
        @Max(value = MAX_RATE, message = "rate.maxValue")
        Byte value,
        @PositiveOrZero(message = "id.positive")
        @Max(value = MAX_ID_VALUE, message = "profile.id.maxValue")
        Long authorId,
        @PositiveOrZero(message = "id.positive")
        @Max(value = MAX_ID_VALUE, message = "profile.id.maxValue")
        Long driverId
) {
}
