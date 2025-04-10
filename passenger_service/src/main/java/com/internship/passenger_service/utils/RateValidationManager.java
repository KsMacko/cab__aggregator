package com.internship.passenger_service.utils;

import com.internship.passenger_service.repo.RateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RateValidationManager {
    private final RateRepo rateRepo;

    public void checkIfRateExists(Long rateId) {
        if (!rateRepo.existsById(rateId))
            throw new RuntimeException("rate.notFound");
    }
}
