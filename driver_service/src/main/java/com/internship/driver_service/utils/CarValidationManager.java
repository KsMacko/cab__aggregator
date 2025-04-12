package com.internship.driver_service.utils;

import com.internship.driver_service.entity.Car;
import com.internship.driver_service.repo.CarRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class CarValidationManager {
    private final CarRepo carRepo;

    public void checkCarNotNull(Car car) {
        if (isNull(car))
            throw new RuntimeException("car.notFound");
    }
}
