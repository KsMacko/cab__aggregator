package com.internship.ride_service.util;

import com.internship.ride_service.entity.Ride;
import com.internship.ride_service.repo.RideRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.internship.ride_service.util.exceptions.ExceptionCodes.RIDE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class RideValidationManager {
    private final RideRepo rideRepo;

    public void checkIfExistsById(String id) {
        if (!rideRepo.existsById(id)) {
            throw new RuntimeException(RIDE_NOT_FOUND.getCode());
        }
    }

    public Ride getRideByIdIfExists(String id) {
        return rideRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(RIDE_NOT_FOUND.getCode()));
    }
}
