package com.internship.passenger_service.controller;

import com.internship.passenger_service.controller.doc.CommandDoc;
import com.internship.passenger_service.dto.ProfileDto;
import com.internship.passenger_service.service.CommandPassengerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/passengers")
@RequiredArgsConstructor
public class CommandPassengerController implements CommandDoc {
    private final CommandPassengerProfileService profileService;
    @Override
    @PostMapping
    public ResponseEntity<ProfileDto> createPassenger(@RequestBody ProfileDto profileDto) {
        ProfileDto createdProfile = profileService.createNewPassengerProfile(profileDto);
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
    @PutMapping
    public ProfileDto updatePassenger(@RequestBody ProfileDto profileDto) {
        return profileService.updatePassengerProfile(profileDto);
    }
    @Override
    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id) {
        profileService.deletePassengerProfile(id);
    }

}
