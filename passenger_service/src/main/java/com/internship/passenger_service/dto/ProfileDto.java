package com.internship.passenger_service.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.experimental.FieldNameConstants;

import static com.internship.passenger_service.utils.ValidationConstants.CYRILLIC_REGEX;
import static com.internship.passenger_service.utils.ValidationConstants.EMAIL_PATTERN;
import static com.internship.passenger_service.utils.ValidationConstants.MAX_EMAIL_LENGTH;
import static com.internship.passenger_service.utils.ValidationConstants.MAX_ID_VALUE;
import static com.internship.passenger_service.utils.ValidationConstants.MAX_NAME_LENGTH;
import static com.internship.passenger_service.utils.ValidationConstants.PHONE_PATTERN;

@FieldNameConstants
@Builder
public record ProfileDto(
        @Positive(message = "id.positive")
        @Max(value = MAX_ID_VALUE, message = "profile.id.maxValue")
        Long profileId,
        @Pattern(regexp = CYRILLIC_REGEX, message = "firstName.invalidInput")
        @Size(max = MAX_NAME_LENGTH, message = "firstName.size")
        @NotBlank(message = "firstName.notBlank")
        String firstName,
        String activatedPromoCodeId,
        @Pattern(regexp = EMAIL_PATTERN, message = "email.invalidInput")
        @Size(max = MAX_EMAIL_LENGTH, message = "email.invalidSize")
        @NotBlank(message = "email.notBlank")
        String email,
        @Pattern(regexp = PHONE_PATTERN, message = "phone.invalidInput")
        @NotBlank(message = "phone.notBlank")
        String phone
) {
}
