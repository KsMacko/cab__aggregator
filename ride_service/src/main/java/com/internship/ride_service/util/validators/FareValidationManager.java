package com.internship.ride_service.util.validators;

import com.internship.ride_service.entity.Fare;
import com.internship.ride_service.enums.FareType;
import com.internship.ride_service.repo.FareRepo;
import com.internship.ride_service.util.exceptions.InvalidInputException;
import com.internship.ride_service.util.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

import static com.internship.ride_service.util.exceptions.ExceptionCodes.FARE_ALREADY_EXISTS;
import static com.internship.ride_service.util.exceptions.ExceptionCodes.FARE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class FareValidationManager {
    private final FareRepo fareRepo;

    public Fare getFareIfExists(FareType fareType) {
        return fareRepo.findFareByType(fareType)
                .orElseThrow(() -> new ResourceNotFoundException(FARE_NOT_FOUND.getCode()));
    }

    public void checkForDuplicateType(FareType fareType) {
        if (fareRepo.existsById(fareType)) {
            throw new InvalidInputException(FARE_ALREADY_EXISTS.getCode());
        }
    }

    public void checkIfNotExistsByType(FareType fareType) {
        if (!fareRepo.existsById(fareType)) {
            throw new RuntimeException(FARE_NOT_FOUND.getCode());
        }
    }
}
