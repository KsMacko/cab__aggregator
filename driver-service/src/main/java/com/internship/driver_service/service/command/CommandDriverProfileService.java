package com.internship.driver_service.service.command;

import com.internship.driver_service.dto.request.RequestProfileDto;
import com.internship.driver_service.dto.request.RequestRateDto;
import com.internship.driver_service.dto.response.ResponseProfileDto;
import com.internship.driver_service.dto.response.ResponseRateDto;
import com.internship.driver_service.dto.mapper.ProfileMapper;
import com.internship.driver_service.dto.mapper.RateMapper;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.repo.DriverProfileRepo;
import com.internship.driver_service.repo.RateRepo;
import com.internship.driver_service.utils.validation.ProfileValidationManager;
import com.internship.driver_service.utils.validation.RateValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommandDriverProfileService {
    private final DriverProfileRepo driverProfileRepo;
    private final ProfileMapper profileMapper;
    private final RateMapper rateMapper;
    private final ProfileValidationManager profileValidationManager;
    private final RateValidationManager rateValidationManager;
    private final RateRepo rateRepo;

    @Transactional
    public ResponseProfileDto createProfile(RequestProfileDto profileDto) {
        profileValidationManager.checkIfPhoneUnique(profileDto.phone());
        DriverProfile driverProfile = profileMapper.handleDto(profileDto);
        return profileMapper.handleEntity(driverProfileRepo.save(driverProfile));
    }

    @Transactional
    public ResponseProfileDto updateDriverProfile(Long profileId, RequestProfileDto profileDto) {
        DriverProfile existingProfile = profileValidationManager.getDriverProfile(profileId);
        profileMapper.updateProfileFromDto(profileDto, existingProfile);

        return profileMapper.handleEntity(driverProfileRepo.save(existingProfile));
    }

    @Transactional
    public void deleteDriverProfile(Long profileId) {
        profileValidationManager.checkIfProfileIdNotNull(profileId);
        profileValidationManager.checkIfProfileExists(profileId);
        driverProfileRepo.deleteById(profileId);
    }

    @Transactional
    public ResponseRateDto setNewRate(RequestRateDto rateDto) {
        profileValidationManager.checkIfProfileExists(rateDto.driverId());
        return rateMapper.handleEntity(rateRepo.save(rateMapper.handleDto(rateDto)));
    }

    @Transactional
    public void deleteRate(Long rateId) {
        rateValidationManager.checkRateExistsById(rateId);
        rateRepo.deleteById(rateId);
    }
}
