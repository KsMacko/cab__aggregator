package com.internship.driver_service.utils;

import com.internship.driver_service.repo.DriverProfileRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileValidationManager {
    private final DriverProfileRepo driverProfileRepo;
    public void checkIfUserExists(Long id) {
        if(driverProfileRepo.findById(id).isEmpty())
            throw new RuntimeException("driver.notFound");
    }
}
