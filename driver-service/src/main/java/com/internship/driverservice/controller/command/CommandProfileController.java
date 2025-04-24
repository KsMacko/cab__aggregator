package com.internship.driverservice.controller.command;

import com.internship.driverservice.controller.doc.ProfileCommandDoc;
import com.internship.driverservice.dto.request.RequestProfileDto;
import com.internship.driverservice.dto.request.RequestRateDto;
import com.internship.driverservice.dto.response.ResponseProfileDto;
import com.internship.driverservice.dto.response.ResponseRateDto;
import com.internship.driverservice.service.command.CommandDriverProfileService;
import com.internship.driverservice.service.command.CommandNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class CommandProfileController implements ProfileCommandDoc {

    private final CommandDriverProfileService commandDriverProfileService;
    private final CommandNotificationService commandNotificationService;

    @Override
    public ResponseEntity<ResponseProfileDto> createProfile(@RequestBody RequestProfileDto profileDto) {
        ResponseProfileDto createdProfile = commandDriverProfileService.createProfile(profileDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProfile.profileId())
                .toUri();
        return ResponseEntity
                .created(location)
                .body(createdProfile);
    }

    @Override
    public ResponseProfileDto updateProfile(@PathVariable Long id,
                                            @RequestBody RequestProfileDto profileDto) {
        return commandDriverProfileService.updateDriverProfile(id, profileDto);
    }

    @Override
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        commandDriverProfileService.deleteDriverProfile(id);
        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<Void> updateCurrentRideStatus(@RequestParam String rideId, @RequestParam String status) {
        commandNotificationService.updateRideStatus(rideId, status);
        return ResponseEntity.noContent().build();
    }
}