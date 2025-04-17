package com.internship.driverservice.service.command;

import com.internship.driverservice.dto.request.RequestProfileDto;
import com.internship.driverservice.dto.request.RequestRateDto;
import com.internship.driverservice.dto.response.ResponseProfileDto;
import com.internship.driverservice.dto.response.ResponseRateDto;
import com.internship.driverservice.dto.mapper.ProfileMapper;
import com.internship.driverservice.dto.mapper.RateMapper;
import com.internship.driverservice.entity.DriverProfile;
import com.internship.driverservice.repo.DriverProfileRepo;
import com.internship.driverservice.repo.RateRepo;
import com.internship.driverservice.utils.validation.ProfileValidationManager;
import com.internship.driverservice.utils.validation.RateValidationManager;
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
