package com.internship.passengerservice.service;

import com.internship.passengerservice.dto.request.RequestProfileDto;
import com.internship.passengerservice.dto.request.RequestRateDto;
import com.internship.passengerservice.dto.response.ResponseProfileDto;
import com.internship.passengerservice.dto.response.ResponseRateDto;
import com.internship.passengerservice.dto.mapper.ProfileMapper;
import com.internship.passengerservice.dto.mapper.RateMapper;
import com.internship.passengerservice.entity.PassengerProfile;
import com.internship.passengerservice.repo.PassengerProfileRepo;
import com.internship.passengerservice.repo.RateRepo;
import com.internship.passengerservice.utils.ProfileValidationManager;
import com.internship.passengerservice.utils.RateValidationManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandPassengerProfileService {
    private final PassengerProfileRepo passengerProfileRepo;
    private final RateRepo rateRepo;
    private final ProfileValidationManager profileValidationManager;
    private final RateValidationManager rateValidationManager;
    private final ProfileMapper profileMapper;
    private final RateMapper rateMapper;

    @Transactional
    public ResponseProfileDto createNewPassengerProfile(RequestProfileDto profileDto) {
        profileValidationManager.checkEmailUniqueness(profileDto.email());
        profileValidationManager.checkPhoneNumberUniqueness(profileDto.phone());
        PassengerProfile savedProfile = passengerProfileRepo.save(profileMapper.handleDto(profileDto));
        return profileMapper.handleEntity(savedProfile);
    }

    @Transactional
    public ResponseProfileDto updatePassengerProfile(Long profileId, RequestProfileDto profileDto) {
        profileValidationManager.checkProfileToUpdate(profileDto);
        profileValidationManager.checkIfProfileExists(profileId);
        PassengerProfile existingProfile = profileValidationManager.getProfileByIdIfExists(profileId);
        profileMapper.updateEntity(profileDto, existingProfile);

        return profileMapper.handleEntity(passengerProfileRepo.save(existingProfile));
    }

    @Transactional
    public void deletePassengerProfile(Long profileId) {
        profileValidationManager.checkIfProfileExists(profileId);
        profileValidationManager.checkIfProfileExists(profileId);
        passengerProfileRepo.deleteById(profileId);
    }

    @Transactional
    public ResponseRateDto setNewRate(RequestRateDto rateDto) {
        profileValidationManager.checkIfProfileExists(rateDto.recipientId());
        return rateMapper.handleEntity(rateRepo.save(rateMapper.handleDto(rateDto)));
    }

    @Transactional
    public void deleteRate(Long driverId, Long rateId) {
        rateValidationManager.checkRateAuthor(driverId, rateId);
        rateRepo.deleteById(rateId);
    }
}
