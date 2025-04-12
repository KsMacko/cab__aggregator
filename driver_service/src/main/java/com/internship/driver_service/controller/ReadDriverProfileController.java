package com.internship.driver_service.controller;

import com.internship.driver_service.controller.doc.ReadDoc;
import com.internship.driver_service.dto.CarDto;
import com.internship.driver_service.dto.ProfileDto;
import com.internship.driver_service.dto.transfer.CarFilterRequest;
import com.internship.driver_service.dto.transfer.CarPackageDto;
import com.internship.driver_service.dto.transfer.DriverFilterRequest;
import com.internship.driver_service.dto.transfer.ProfilePackageDto;
import com.internship.driver_service.service.query.ReadCarService;
import com.internship.driver_service.service.query.ReadDriverProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class ReadDriverProfileController implements ReadDoc {

    private final ReadDriverProfileService readDriverProfileService;
    private final ReadCarService carService;

    @Override
    public ProfilePackageDto findAllDrivers(DriverFilterRequest filter) {
        return readDriverProfileService.readAllDrivers(filter);
    }

    @Override
    public ProfileDto findById(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileById(id);
    }

    @Override
    public CarDto getCurrentCar(@PathVariable Long id) {
        return carService.getCurrentCarByProfileId(id);
    }

    @Override
    public CarPackageDto getCars(CarFilterRequest filter) {
        return carService.readAllCars(filter);
    }
}