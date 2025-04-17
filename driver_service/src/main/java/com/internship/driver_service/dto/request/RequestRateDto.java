package com.internship.driver_service.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import static com.internship.driver_service.utils.validation.ValidationConstants.MAX_ID_VALUE;
import static com.internship.driver_service.utils.validation.ValidationConstants.MAX_RATE;
import static com.internship.driver_service.utils.validation.ValidationConstants.MIN_RATE;

public record RequestRateDto(
        @Min(value = MIN_RATE, message = "rate.minValue")
        @Max(value = MAX_RATE, message = "rate.maxValue")
        @NotNull(message = "value.notNull")
        Byte value,
        @PositiveOrZero(message = "id.positive")
        @Max(value = MAX_ID_VALUE, message = "authorId.id.maxValue")
        @NotNull(message = "authorId.notNull")
        Long authorId,
        @PositiveOrZero(message = "id.positive")
        @Max(value = MAX_ID_VALUE, message = "profile.id.maxValue")
        @NotNull(message = "profile.notNull")
        Long driverId
) {
}
