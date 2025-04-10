package com.internship.driver_service.utils;

import com.internship.driver_service.repo.RateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class RateValidationManager {
    private final RateRepo rateRepo;

    public void checkRateNotNull(Byte rate) {
        if (isNull(rate))
            throw new RuntimeException("driver.profile.rating.notFound");
    }

    public void checkRateExistsById(Long id) {
        if (!rateRepo.existsById(id))
            throw new RuntimeException("rate.notFound");
    }
}
