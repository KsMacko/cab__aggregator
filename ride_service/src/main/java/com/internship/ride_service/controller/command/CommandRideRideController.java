package com.internship.ride_service.controller.command;

import com.internship.ride_service.controller.doc.CommandRideDoc;
import com.internship.ride_service.dto.RideDto;
import com.internship.ride_service.service.command.CommandRideService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/rides")
@RequiredArgsConstructor
public class CommandRideRideController implements CommandRideDoc {

    private final CommandRideService commandRideService;

    @Override
    public ResponseEntity<RideDto> createRide(@Valid @RequestBody RideDto rideDto) {
        RideDto createdProfile = commandRideService.createRide(rideDto);
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

    @Override
    public RideDto updateRide(@Valid @RequestBody RideDto ride) {
        return commandRideService.updateRide(ride);
    }

    @Override
    public RideDto changeRideStatusToAccepted(String rideId, Long driverId) {
        return commandRideService.changeRideStatusToAccepted(rideId, driverId);
    }

    @Override
    public RideDto changeRideStatusToWaitingForPassenger(String rideId) {
        return commandRideService.changeRideStatusToWaitingForPassenger(rideId);
    }

    @Override
    public RideDto changeRideStatusToInProgress(String rideId) {
        return commandRideService.changeRideStatusToInProgress(rideId);
    }

    @Override
    public RideDto changeRideStatusRecalculated(String rideId) {
        return commandRideService.changeRideStatusRecalculated(rideId);
    }

    @Override
    public RideDto changeRideStatusToCompleted(String rideId) {
        return commandRideService.changeRideStatusToCompleted(rideId);
    }
}
