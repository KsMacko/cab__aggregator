package com.internship.passengerservice.utils;

import com.internship.passengerservice.repo.RateRepo;
import com.internship.passengerservice.utils.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.internship.passengerservice.utils.exceptions.ExceptionCodes.RATE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class RateValidationManager {
    private final RateRepo rateRepo;

    public void checkIfRateExists(Long rateId) {
        if (!rateRepo.existsById(rateId)) {
            throw new ResourceNotFoundException(RATE_NOT_FOUND.getCode());
        }
    }
}
