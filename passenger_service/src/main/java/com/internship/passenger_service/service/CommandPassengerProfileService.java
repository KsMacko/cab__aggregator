package com.internship.passenger_service.service;

import com.internship.passenger_service.dto.ProfileDto;
import com.internship.passenger_service.dto.RateDto;
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
    public ProfileDto createNewPassengerProfile(ProfileDto profileDto) {
        profileValidationManager.checkEmailUniqueness(profileDto.email());
        profileValidationManager.checkPhoneNumberUniqueness(profileDto.phone());
        PassengerProfile savedProfile = passengerProfileRepo.save(profileMapper.handleDto(profileDto));
        return profileMapper.handleEntity(savedProfile);
    }

    @Transactional
    public ProfileDto updatePassengerProfile(ProfileDto profileDto) {
        profileValidationManager.checkProfileToUpdate(profileDto);
        profileValidationManager.checkIfProfileExists(profileDto.profileId());
        PassengerProfile existingProfile = profileValidationManager.getProfileByIdIfExists(profileDto.profileId());
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
    public RateDto setNewRate(RateDto rateDto) {
        profileValidationManager.checkIfProfileExists(rateDto.id());
        return rateMapper.handleEntity(rateRepo.save(rateMapper.handleDto(rateDto)));
    }

    @Transactional
    public void deleteRate(Long rateId) {
        rateValidationManager.checkIfRateExists(rateId);
        rateRepo.deleteById(rateId);
    }
}
