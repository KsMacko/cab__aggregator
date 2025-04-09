package com.internship.passenger_service.utils;

import static java.util.Objects.isNull;

import com.internship.passenger_service.entity.PassengerProfile;
import com.internship.passenger_service.repo.PassengerProfileRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileValidationManager {
    private final PassengerProfileRepo passengerProfileRepo;
    public void checkIfProfileExists(Long profileId) {
        if(!passengerProfileRepo.existsById(profileId)){
            throw new RuntimeException("passenger.notFound");
        }
    }
    public void checkIfIdNotNull(Long profileId) {
        if(isNull(profileId))
            throw new RuntimeException("passenger.id.notNull");
    }
    public PassengerProfile getProfileByIdIfExists(Long profileId) {
        return passengerProfileRepo.findById(profileId)
                .orElseThrow(() -> new RuntimeException("passenger.notFound"));
    }
}
