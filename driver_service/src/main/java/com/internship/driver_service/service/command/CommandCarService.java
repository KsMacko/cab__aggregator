package com.internship.driver_service.service.command;

import com.internship.driver_service.dto.CarDto;
import com.internship.driver_service.dto.mapper.CarMapper;
import com.internship.driver_service.entity.Car;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.repo.CarRepo;
import com.internship.driver_service.utils.validation.CarValidationManager;
import com.internship.driver_service.utils.validation.ProfileValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandCarService {
    private final CarRepo carRepo;
    private final ProfileValidationManager profileValidationManager;
    private final CarValidationManager carValidationManager;

    @Transactional
    public CarDto addNewCar(CarDto carDto) {
        DriverProfile driverProfile = profileValidationManager.getDriverProfile(carDto.driverId());
        carValidationManager.validateCarNumberUniqueness(carDto.carNumber());
        Car car = CarMapper.converter.handleDto(carDto);
        car.setDriverProfile(driverProfile);
        return CarMapper.converter.handleEntity(carRepo.save(car));
    }

    @Transactional
    public void deleteCar(Long carId) {
        Car car = carValidationManager.getIfExistsById(carId);
        carRepo.delete(car);
    }

    @Transactional
    public CarDto setCurrentCar(Long carId) {
        Car car = carValidationManager.getIfExistsById(carId);
        resetCurrentCarForDriver(car.getDriverProfile().getProfileId());
        car.setIsCurrent(true);
        return CarMapper.converter.handleEntity(carRepo.save(car));
    }

    private void resetCurrentCarForDriver(Long driverId) {
        List<Car> cars = carRepo.findAllByDriverProfile_ProfileId(driverId);
        cars.forEach(car -> car.setIsCurrent(false));
        carRepo.saveAll(cars);
    }
}
