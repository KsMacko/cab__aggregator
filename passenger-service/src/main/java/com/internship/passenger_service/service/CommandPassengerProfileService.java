package com.internship.passenger_service.service;

import com.internship.passenger_service.dto.request.RequestProfileDto;
import com.internship.passenger_service.dto.response.ResponseProfileDto;
import com.internship.passenger_service.dto.response.ResponseRateDto;
import com.internship.passenger_service.dto.mapper.ProfileMapper;
import com.internship.passenger_service.dto.mapper.RateMapper;
import com.internship.passenger_service.entity.PassengerProfile;
import com.internship.passenger_service.repo.PassengerProfileRepo;
import com.internship.passenger_service.repo.RateRepo;
import com.internship.passenger_service.utils.ProfileValidationManager;
import com.internship.passenger_service.utils.RateValidationManager;
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
    public ResponseRateDto setNewRate(ResponseRateDto rateDto) {
        profileValidationManager.checkIfProfileExists(rateDto.id());
        return rateMapper.handleEntity(rateRepo.save(rateMapper.handleDto(rateDto)));
    }

    @Transactional
    public void deleteRate(Long rateId) {
        rateValidationManager.checkIfRateExists(rateId);
        rateRepo.deleteById(rateId);
    }
}
