package com.internship.rideservice.util.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidOffsetDateValidator.class)
public @interface ValidateDate {
    String message() default "date.invalidFormat";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}