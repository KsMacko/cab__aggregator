package com.internship.driver_service.utils.validation;

import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.repo.DriverProfileRepo;
import com.internship.driver_service.utils.exceptions.InvalidInputException;
import com.internship.driver_service.utils.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.internship.driver_service.utils.exceptions.ExceptionCodes.DRIVER_ID_NOT_NULL;
import static com.internship.driver_service.utils.exceptions.ExceptionCodes.DRIVER_NOT_FOUND;
import static com.internship.driver_service.utils.exceptions.ExceptionCodes.DRIVER_PHONE_UNIQUE;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class ProfileValidationManager {
    private final DriverProfileRepo driverProfileRepo;

    public void checkIfProfileExists(Long id) {
        if (driverProfileRepo.findById(id).isEmpty())
            throw new ResourceNotFoundException(DRIVER_NOT_FOUND.getCode());
    }

    public void checkIfProfileIdNotNull(Long id) {
        if (isNull(id))
            throw new InvalidInputException(DRIVER_ID_NOT_NULL.getCode());
    }

    public DriverProfile getDriverProfile(Long id) {
        return driverProfileRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(DRIVER_NOT_FOUND.getCode()));
    }

    public void checkIfPhoneUnique(String phone) {
        if (driverProfileRepo.existsByPhone(phone))
            throw new InvalidInputException(DRIVER_PHONE_UNIQUE.getCode());
    }
}
