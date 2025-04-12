package com.internship.driver_service.utils;

import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.repo.DriverProfileRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class ProfileValidationManager {
    private final DriverProfileRepo driverProfileRepo;

    public void checkIfProfileExists(Long id) {
        if (driverProfileRepo.findById(id).isEmpty())
            throw new RuntimeException("driver.notFound");
    }

    public void checkIfProfileIdNotNull(Long id) {
        if (isNull(id))
            throw new RuntimeException("driver.id.notNull");
    }

    public DriverProfile getDriverProfile(Long id) {
        return driverProfileRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("driver.notFound"));
    }
}
