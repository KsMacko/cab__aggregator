package com.internship.driverservice.service.command;

import com.internship.driverservice.dto.request.RequestCarDto;
import com.internship.driverservice.dto.response.ResponseCarDto;
import com.internship.driverservice.dto.mapper.CarMapper;
import com.internship.driverservice.entity.Car;
import com.internship.driverservice.entity.DriverProfile;
import com.internship.driverservice.repo.CarRepo;
import com.internship.driverservice.utils.validation.CarValidationManager;
import com.internship.driverservice.utils.validation.ProfileValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandCarService {
    private final CarRepo carRepo;
    private final CarMapper carMapper;
    private final ProfileValidationManager profileValidationManager;
    private final CarValidationManager carValidationManager;

    @Transactional
    public Car addNewCar(RequestCarDto carDto) {
        DriverProfile driverProfile = profileValidationManager.getDriverProfile(carDto.driverId());
        carValidationManager.validateCarNumberUniqueness(carDto.carNumber());
        Car car = carMapper.handleDto(carDto);
        car.setDriverProfile(driverProfile);
        return carRepo.save(car);
    }

    @Transactional
    public void deleteCar(Long carId) {
        Car car = carValidationManager.getIfExistsById(carId);
        carRepo.delete(car);
    }

    @Transactional
    public Car setCurrentCar(Long carId) {
        Car car = carValidationManager.getIfExistsById(carId);
        resetCurrentCarForDriver(car.getDriverProfile().getProfileId());
        car.setIsCurrent(true);
        return carRepo.save(car);
    }

    private void resetCurrentCarForDriver(Long driverId) {
        List<Car> cars = carRepo.findAllByDriverProfile_ProfileId(driverId);
        cars.forEach(car -> car.setIsCurrent(false));
        carRepo.saveAll(cars);
    }
}
