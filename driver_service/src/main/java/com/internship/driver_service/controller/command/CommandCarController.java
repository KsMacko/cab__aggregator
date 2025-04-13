package com.internship.driver_service.controller.command;

import com.internship.driver_service.controller.doc.CarCommandDoc;
import com.internship.driver_service.dto.CarDto;
import com.internship.driver_service.service.command.CommandCarService;
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

    @Override
    public ResponseEntity<Void> setCurrentCar(@PathVariable Long id) {
        commandCarService.setCurrentCar(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public CarDto addNewCar(@RequestBody CarDto carDto) {
        return commandCarService.addNewCar(carDto);
    }

    @Override
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        commandCarService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
