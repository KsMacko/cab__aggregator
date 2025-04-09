package com.internship.passenger_service.utils;

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
        if(profileId==null)
            throw new RuntimeException("passenger.id.notNull");
    }
}
