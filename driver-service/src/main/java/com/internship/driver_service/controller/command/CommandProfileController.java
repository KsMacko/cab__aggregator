package com.internship.driver_service.controller.command;

import com.internship.driver_service.controller.doc.ProfileCommandDoc;
import com.internship.driver_service.dto.request.RequestProfileDto;
import com.internship.driver_service.dto.request.RequestRateDto;
import com.internship.driver_service.dto.response.ResponseProfileDto;
import com.internship.driver_service.dto.response.ResponseRateDto;
import com.internship.driver_service.service.command.CommandDriverProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class CommandProfileController implements ProfileCommandDoc {

    private final CommandDriverProfileService commandDriverProfileService;

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
    public ResponseRateDto setRateToDriver(@RequestBody RequestRateDto rateDto) {
        return commandDriverProfileService.setNewRate(rateDto);
    }

    @Override
    public ResponseEntity<Void> deleteRateFromDriver(@PathVariable Long id) {
        commandDriverProfileService.deleteRate(id);
        return ResponseEntity.noContent().build();
    }
}