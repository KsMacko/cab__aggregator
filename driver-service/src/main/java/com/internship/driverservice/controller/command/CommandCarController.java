package com.internship.driverservice.controller.command;

import com.internship.driverservice.controller.doc.CarCommandDoc;
import com.internship.driverservice.dto.mapper.CarMapper;
import com.internship.driverservice.dto.request.RequestCarDto;
import com.internship.driverservice.dto.response.ResponseCarDto;
import com.internship.driverservice.service.command.CommandCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/drivers/cars")
@RequiredArgsConstructor
public class CommandCarController implements CarCommandDoc {
    private final CommandCarService commandCarService;
    private final CarMapper carMapper;

    @Override
    public ResponseEntity<ResponseCarDto> setCurrentCar(@PathVariable Long id) {
        return ResponseEntity.ok(carMapper.handleEntity(commandCarService.setCurrentCar(id)));
    }

    @Override
    public ResponseEntity<ResponseCarDto> addNewCar(@RequestBody RequestCarDto carDto) {
        return ResponseEntity.ok(carMapper.handleEntity(commandCarService.addNewCar(carDto)));
    }

    @Override
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        commandCarService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
