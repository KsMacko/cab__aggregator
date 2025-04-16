package com.internship.ride_service.controller.command;

import com.internship.ride_service.controller.doc.CommandFareDoc;
import com.internship.ride_service.dto.FareDto;
import com.internship.ride_service.enums.FareType;
import com.internship.ride_service.service.command.CommandFareService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/fares")
@RequiredArgsConstructor
public class CommandFareController implements CommandFareDoc {

    private final CommandFareService commandFareService;

    @Override
    public ResponseEntity<FareDto> createFare(@Valid @RequestBody FareDto fareDto) {
        FareDto createdFare = commandFareService.createFare(fareDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{type}")
                .buildAndExpand(createdFare.type())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(createdFare);
    }

    @Override
    public ResponseEntity<Void> deleteFare(@PathVariable FareType type) {
        commandFareService.deleteFare(type);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<FareDto> updateFare(@RequestBody FareDto fareDto) {
        FareDto updatedFare = commandFareService.updateFare(fareDto);
        return ResponseEntity.ok(updatedFare);
    }
}