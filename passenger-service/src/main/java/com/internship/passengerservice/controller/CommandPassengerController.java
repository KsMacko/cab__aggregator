package com.internship.passengerservice.controller;

import com.internship.passengerservice.controller.doc.CommandDoc;
import com.internship.passengerservice.dto.mapper.ProfileMapper;
import com.internship.passengerservice.dto.request.RequestProfileDto;
import com.internship.passengerservice.dto.response.ResponseProfileDto;
import com.internship.passengerservice.service.CommandPassengerProfileService;
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
    private final ProfileMapper profileMapper;
    @Override
    @PostMapping
    public ResponseEntity<ResponseProfileDto> createPassenger(@RequestBody RequestProfileDto profileDto) {
        ResponseProfileDto createdProfile = profileService.createNewPassengerProfile(profileDto);
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
    @PutMapping("/{id}")
    public ResponseEntity<ResponseProfileDto> updatePassenger(@PathVariable Long id,
                                              @RequestBody RequestProfileDto profileDto) {
        return ResponseEntity.ok(profileMapper.handleEntity(profileService.updatePassengerProfile(id, profileDto)));
    }
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        profileService.deletePassengerProfile(id);
        return ResponseEntity.noContent().build();
    }


}
