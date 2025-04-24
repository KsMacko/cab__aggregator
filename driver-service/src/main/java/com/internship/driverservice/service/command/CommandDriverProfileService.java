package com.internship.driverservice.service.command;

import com.internship.driverservice.dto.request.RequestProfileDto;
import com.internship.driverservice.dto.response.ResponseProfileDto;
import com.internship.driverservice.dto.mapper.ProfileMapper;
import com.internship.driverservice.entity.DriverProfile;
import com.internship.driverservice.enums.DriverStatus;
import com.internship.driverservice.repo.DriverProfileRepo;
import com.internship.driverservice.service.communication.FinanceFeignClient;
import com.internship.driverservice.utils.validation.ProfileValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommandDriverProfileService {
    private final DriverProfileRepo driverProfileRepo;
    private final ProfileMapper profileMapper;
    private final ProfileValidationManager profileValidationManager;
    private final FinanceFeignClient financeFeignClient;

    @Transactional
    public ResponseProfileDto createProfile(RequestProfileDto profileDto) {
        profileValidationManager.checkIfPhoneUnique(profileDto.phone());
        DriverProfile driverProfile = profileMapper.handleDto(profileDto);
        driverProfile.setDriverStatus(DriverStatus.FREE);
        DriverProfile savedProfile = driverProfileRepo.save(driverProfile);
        financeFeignClient.createWallet(savedProfile.getProfileId());
        return profileMapper.handleEntity(savedProfile);
    }

    @Transactional
    public ResponseProfileDto updateDriverProfile(Long profileId, RequestProfileDto profileDto) {
        profileValidationManager.checkIfPhoneUnique(profileDto.phone());
        DriverProfile existingProfile = profileValidationManager.getDriverProfile(profileId);
        profileMapper.updateProfileFromDto(profileDto, existingProfile);
        return profileMapper.handleEntity(driverProfileRepo.save(existingProfile));
    }

    @Transactional
    public void deleteDriverProfile(Long profileId) {
        profileValidationManager.checkIfProfileExists(profileId);
        financeFeignClient.deleteWallet(profileId);
        driverProfileRepo.deleteById(profileId);
    }


}
