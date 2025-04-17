package com.internship.driverservice.utils.validation;

import com.internship.driverservice.entity.Car;
import com.internship.driverservice.repo.CarRepo;
import com.internship.driverservice.utils.exceptions.InvalidInputException;
import com.internship.driverservice.utils.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.internship.driverservice.utils.exceptions.ExceptionCodes.CAR_NOT_FOUND;
import static com.internship.driverservice.utils.exceptions.ExceptionCodes.CAR_NUMBER_ALREADY_EXISTS;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class CarValidationManager {
    private final CarRepo carRepo;

    public void checkCarNotNull(Car car) {
        if (isNull(car))
            throw new ResourceNotFoundException(CAR_NOT_FOUND.getCode());
    }

    public void validateCarNumberUniqueness(String carNumber) {
        if (carRepo.existsByCarNumberIgnoreCase(carNumber)) {
            throw new InvalidInputException(CAR_NUMBER_ALREADY_EXISTS.getCode());
        }
    }

    public Car getIfExistsById(Long id) {
        return carRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CAR_NOT_FOUND.getCode()));
    }
}
