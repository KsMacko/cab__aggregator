package com.internship.passengerservice.controller;

import com.internship.passengerservice.controller.doc.ReadDoc;
import com.internship.passengerservice.dto.response.ResponseProfileDto;
import com.internship.passengerservice.dto.transfer.DataPackageDto;
import com.internship.passengerservice.dto.transfer.ProfileFilterRequest;
import com.internship.passengerservice.service.ReadPassengerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/passengers")
@RequiredArgsConstructor
public class ReadPassengerController implements ReadDoc {
    private final ReadPassengerProfileService profileService;

    @Override
    public DataPackageDto readAllProfiles(ProfileFilterRequest profileFilterRequest) {
        return profileService.readPassengerProfiles(profileFilterRequest);
    }
    @Override
    public ResponseProfileDto readPassengerById(@PathVariable("id") Long id) {
        return profileService.readPassengerProfile(id);
    }
}
