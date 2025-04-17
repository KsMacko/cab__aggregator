package com.internship.driver_service.utils.validation;

import com.internship.driver_service.repo.RateRepo;
import com.internship.driver_service.utils.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.internship.driver_service.utils.exceptions.ExceptionCodes.RATE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class RateValidationManager {
    private final RateRepo rateRepo;

    public void checkRateExistsById(Long id) {
        if (!rateRepo.existsById(id))
            throw new ResourceNotFoundException(RATE_NOT_FOUND.getCode());
    }
}
