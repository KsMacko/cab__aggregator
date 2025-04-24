package com.internship.rideservice.controller.command;

import com.internship.commonevents.event.RideParticipantsConfirmation;
import com.internship.rideservice.controller.doc.CommandRideDoc;
import com.internship.rideservice.dto.request.RequestRideDto;
import com.internship.rideservice.dto.response.ResponseRideDto;
import com.internship.rideservice.service.command.CommandRideService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/rides")
@RequiredArgsConstructor
public class CommandRideController implements CommandRideDoc {

    private final CommandRideService commandRideService;

    @Override
    public ResponseEntity<ResponseRideDto> createRide(@Valid @RequestBody RequestRideDto rideDto) {
        ResponseRideDto createdProfile = commandRideService.createRide(rideDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProfile.id())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(createdProfile);
    }

    @Override
    public ResponseEntity<Void> deleteRide(@RequestParam String id) {
        commandRideService.deleteRide(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/participants")
    RideParticipantsConfirmation checkParticipants(@PathVariable String id){
        return commandRideService.findDriverAndPassengerByRideId(id);
    }


}
