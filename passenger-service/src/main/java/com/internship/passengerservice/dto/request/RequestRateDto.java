package com.internship.passengerservice.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import static com.internship.passengerservice.utils.ValidationConstants.MAX_ID_VALUE;
import static com.internship.passengerservice.utils.ValidationConstants.MAX_RATE;
import static com.internship.passengerservice.utils.ValidationConstants.MIN_RATE;

public record RequestRateDto(
        @Min(value = MIN_RATE, message = "rate.minValue")
        @Max(value = MAX_RATE, message = "rate.maxValue")
        @NotNull(message = "rate.notNull")
        Byte value,
        @Positive(message = "id.positive")
        @Max(value = MAX_ID_VALUE, message = "profile.id.maxValue")
        Long authorId
) {
}
