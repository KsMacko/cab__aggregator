package com.internship.driver_service.controller.query;

import com.internship.driver_service.controller.doc.CarReadDoc;
import com.internship.driver_service.dto.response.ResponseCarDto;
import com.internship.driver_service.dto.transfer.CarFilterRequest;
import com.internship.driver_service.dto.transfer.CarPackageDto;
import com.internship.driver_service.service.query.ReadCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/drivers/cars")
@RequiredArgsConstructor
public class ReadCarController implements CarReadDoc {
    private final ReadCarService carService;

    @Override
    public ResponseCarDto getCurrentCar(@PathVariable Long id) {
        return carService.getCurrentCarByProfileId(id);
    }

    @Override
    public CarPackageDto getCars(CarFilterRequest filter) {
        return carService.readAllCars(filter);
    }
}
