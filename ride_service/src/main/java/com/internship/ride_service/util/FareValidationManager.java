package com.internship.ride_service.util;

import com.internship.ride_service.entity.Fare;
import com.internship.ride_service.enums.FareType;
import com.internship.ride_service.repo.FareRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FareValidationManager {
    private final FareRepo fareRepo;

    public Fare getFareIfExists(FareType fareType) {
        return fareRepo.findFareByType(fareType)
                .orElseThrow(() -> new RuntimeException("fare.notFound"));
    }

    public void checkForDuplicateType(FareType fareType) {
        if (fareRepo.existsById(fareType))
            throw new RuntimeException("fare.alreadyExists");
    }

    public void checkIfNotExistsByType(FareType fareType) {
        if (!fareRepo.existsById(fareType))
            throw new RuntimeException("fare.notFound");
    }
}
