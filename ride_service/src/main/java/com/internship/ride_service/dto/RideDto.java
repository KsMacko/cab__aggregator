package com.internship.ride_service.dto;

import com.internship.ride_service.enums.RideStatus;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.List;

import static com.internship.ride_service.util.validators.ValidationConstants.ALPHANUMERIC_REGEX;
import static com.internship.ride_service.util.validators.ValidationConstants.FARE_TYPE_PATTERN;
import static com.internship.ride_service.util.validators.ValidationConstants.MAX_ADDRESS_LENGTH;
import static com.internship.ride_service.util.validators.ValidationConstants.MAX_DISTANCE;
import static com.internship.ride_service.util.validators.ValidationConstants.MAX_ID_VALUE;
import static com.internship.ride_service.util.validators.ValidationConstants.MAX_PROMO_CODE_LENGTH;
import static com.internship.ride_service.util.validators.ValidationConstants.MIN_ADDRESS_LENGTH;
import static com.internship.ride_service.util.validators.ValidationConstants.MIN_DISTANCE;

public record RideDto(
        String id,
        Long driverId,
        @NotNull(message = "passenger.notNull")
        @PositiveOrZero(message = "id.positive")
        @Max(value = MAX_ID_VALUE, message = "profile.id.maxValue")
        Long passengerId,
        @Pattern(regexp = ALPHANUMERIC_REGEX, message = "promo.code.invalidInput")
        @Size(max = MAX_PROMO_CODE_LENGTH, message = "promo.code.invalidSize")
        String promoCode,
        @Size(min = MIN_ADDRESS_LENGTH, max = MAX_ADDRESS_LENGTH, message = "start.location.invalidSize")
        String startLocation,
        @NotEmpty(message = "location.list.notNull")
        List<@NotBlank(message = "location.list.elements.notBlank")
        @Size(min = MIN_ADDRESS_LENGTH, max = MAX_ADDRESS_LENGTH, message = "location.list.invalidSize")
                String> endLocation,
        OffsetDateTime createdAt,
        OffsetTime startWaitingTime,
        OffsetTime startTime,
        OffsetTime endTime,
        @NotNull(message = "distance.notNull")
        @DecimalMin(value = MIN_DISTANCE, inclusive = false, message = "distance.positive")
        @DecimalMax(value = MAX_DISTANCE, message = "distance.maxValue")
        Float distance,
        RideStatus status,
        @Pattern(regexp = FARE_TYPE_PATTERN, message = "fare.type.invalidInput")
        String fareType,
        BigDecimal price
) {
}
