package com.internship.driverservice.utils.validation;

import com.internship.driverservice.repo.RateRepo;
import com.internship.driverservice.utils.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.internship.driverservice.utils.exceptions.ExceptionCodes.RATE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class RateValidationManager {
    private final RateRepo rateRepo;

    public void checkRateExistsById(Long id) {
        if (!rateRepo.existsById(id))
            throw new ResourceNotFoundException(RATE_NOT_FOUND.getCode());
    }
}
