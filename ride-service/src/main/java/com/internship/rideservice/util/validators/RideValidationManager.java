package com.internship.rideservice.util.validators;

import com.internship.rideservice.entity.Ride;
import com.internship.rideservice.repo.RideRepo;
import com.internship.rideservice.util.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.internship.rideservice.util.exceptions.ExceptionCodes.RIDE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class RideValidationManager {
    private final RideRepo rideRepo;

    public void checkIfExistsById(String id) {
        if (!rideRepo.existsById(id)) {
            throw new ResourceNotFoundException(RIDE_NOT_FOUND.getCode());
        }
    }

    public Ride getRideByIdIfExists(String id) {
        return rideRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(RIDE_NOT_FOUND.getCode()));
    }
}
