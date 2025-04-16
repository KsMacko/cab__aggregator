package com.internship.ride_service.util.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ValidLocationList implements ConstraintValidator<ValidateLocationList, List<String>> {

    @Override
    public boolean isValid(List<String> locations, ConstraintValidatorContext context) {
        if (locations == null || locations.isEmpty()) {
            return false;
        }
        for (String location : locations) {
            if (location == null ||
                    location.length() < ValidationConstants.MIN_ADDRESS_LENGTH ||
                    location.length() > ValidationConstants.MAX_ADDRESS_LENGTH) {
                return false;
            }
        }
        return true;
    }
}