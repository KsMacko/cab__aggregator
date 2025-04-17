package com.internship.rideservice.controller.command;

import com.internship.rideservice.controller.doc.CommandFareDoc;
import com.internship.rideservice.dto.request.RequestFareDto;
import com.internship.rideservice.dto.response.ResponseFareDto;
import com.internship.rideservice.enums.FareType;
import com.internship.rideservice.service.command.CommandFareService;
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
    public ResponseEntity<ResponseFareDto> createFare(@Valid @RequestBody RequestFareDto fareDto) {
        ResponseFareDto createdFare = commandFareService.createFare(fareDto);
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
    public ResponseEntity<ResponseFareDto> updateFare(@RequestBody RequestFareDto fareDto) {
        ResponseFareDto updatedFare = commandFareService.updateFare(fareDto);
        return ResponseEntity.ok(updatedFare);
    }
}