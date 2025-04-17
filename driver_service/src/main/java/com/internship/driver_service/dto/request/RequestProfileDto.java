package com.internship.driver_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import static com.internship.driver_service.utils.validation.ValidationConstants.CYRILLIC_REGEX;
import static com.internship.driver_service.utils.validation.ValidationConstants.DRIVER_STATUS_PATTERN;
import static com.internship.driver_service.utils.validation.ValidationConstants.FARE_TYPE_PATTERN;
import static com.internship.driver_service.utils.validation.ValidationConstants.MAX_NAME_LENGTH;
import static com.internship.driver_service.utils.validation.ValidationConstants.PHONE_PATTERN;

public record RequestProfileDto(
        @Pattern(regexp = CYRILLIC_REGEX, message = "firstName.invalidInput")
        @Size(max = MAX_NAME_LENGTH, message = "firstName.size")
        @NotBlank(message = "firstName.notBlank")
        String firstName,

        @Pattern(regexp = CYRILLIC_REGEX, message = "lastName.invalidInput")
        @Size(max = MAX_NAME_LENGTH, message = "lastName.size")
        @NotBlank(message = "lastName.notBlank")
        String lastName,

        @Pattern(regexp = FARE_TYPE_PATTERN, message = "fareType.invalidInput")
        @NotBlank(message = "fareType.notBlank")
        String fareType,

        @Pattern(regexp = DRIVER_STATUS_PATTERN, message = "driverStatus.invalidInput")
        @NotBlank(message = "driverStatus.notBlank")
        String driverStatus,

        @Pattern(regexp = PHONE_PATTERN, message = "phone.invalidInput")
        @NotBlank(message = "phone.notBlank")
        String phone
)
{

}
