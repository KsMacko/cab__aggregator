package com.internship.ride_service.util;


import com.internship.ride_service.entity.Ride;
import com.internship.ride_service.repo.RideRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RideValidationManager {
    private final RideRepo rideRepo;

    public void checkIfExistsById(String id) {
        if (!rideRepo.existsById(id))
            throw new RuntimeException("ride.notFound");
    }

    public Ride getRideByIdIfExists(String id) {
        return rideRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("ride.notFound"));
    }
}
