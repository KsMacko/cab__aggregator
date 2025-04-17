package com.internship.driver_service.controller.command;

import com.internship.driver_service.controller.doc.CarCommandDoc;
import com.internship.driver_service.dto.request.RequestCarDto;
import com.internship.driver_service.dto.response.ResponseCarDto;
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
    public ResponseCarDto setCurrentCar(@PathVariable Long id) {
        return commandCarService.setCurrentCar(id);
    }

    @Override
    public ResponseCarDto addNewCar(@RequestBody RequestCarDto carDto) {
        return commandCarService.addNewCar(carDto);
    }

    @Override
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        commandCarService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }
}
