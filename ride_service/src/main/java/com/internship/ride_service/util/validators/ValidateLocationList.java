package com.internship.ride_service.util.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidLocationList.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateLocationList {
    String message() default "ride.endLocation.invalidInput";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
