package com.internship.driver_service.dto;

import com.internship.driver_service.utils.validation.ValidationConstants;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CarDto(
        @PositiveOrZero(message = "id.positive")
        @Max(value = ValidationConstants.MAX_ID_VALUE, message = "id.maxValue")
        Long id,

        @PositiveOrZero(message = "id.positive")
        @Max(value = ValidationConstants.MAX_ID_VALUE, message = "driver.id.maxValue")
        Long driverId,

        Boolean isCurrent,

        @Pattern(regexp = ValidationConstants.CAR_NUM_PATTERN, message = "car.number.invalidInput")
        @Size(max = ValidationConstants.MAX_CAR_NUMBER_LENGTH, message = "car.number.size")
        String carNumber,

        @Pattern(regexp = ValidationConstants.LATIN_PATTERN, message = "brand.invalidInput")
        @Size(min = ValidationConstants.MIN_CAR_BRAND_LENGTH, max = ValidationConstants.MAX_CAR_BRAND_LENGTH, message = "car.brand.size")
        String brand,

        @Pattern(regexp = ValidationConstants.CYRILLIC_REGEX, message = "color.invalidInput")
        @Size(max = ValidationConstants.MAX_NAME_LENGTH, message = "color.size")
        String color
) {
}