package com.internship.driver_service.service.command;

import com.internship.driver_service.dto.ProfileDto;
import com.internship.driver_service.dto.RateDto;
import com.internship.driver_service.dto.mapper.ProfileMapper;
import com.internship.driver_service.dto.mapper.RateMapper;
import com.internship.driver_service.entity.DriverAccount;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.repo.DriverAccountRepo;
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
    private final DriverAccountRepo driverAccountRepo;
    private final ProfileValidationManager profileValidationManager;
    private final RateValidationManager rateValidationManager;
    private final RateRepo rateRepo;

    @Transactional
    public ProfileDto createProfile(ProfileDto profileDto) {
        DriverAccount driverAccount = driverAccountRepo.findById(profileDto.profileId())
                .orElseThrow(() -> new RuntimeException("driver.account.notExists"));
        DriverProfile driverProfile = ProfileMapper.converter.handleDto(profileDto);
        driverProfile.setDriverAccount(driverAccount);
        driverProfile.setProfileId(null);
        driverAccount.setDriverProfile(driverProfile);
        return ProfileMapper.converter.handleEntity(driverProfileRepo.save(driverProfile));
    }

    @Transactional
    public ProfileDto updateDriverProfile(ProfileDto profileDto) {
        profileValidationManager.checkIfProfileIdNotNull(profileDto.profileId());
        DriverProfile existingProfile = profileValidationManager.getDriverProfile(profileDto.profileId());
        ProfileMapper.converter.updateProfileFromDto(profileDto, existingProfile);

        return ProfileMapper.converter.handleEntity(driverProfileRepo.save(existingProfile));
    }

    @Transactional
    public void deleteDriverProfile(Long profileId) {
        profileValidationManager.checkIfProfileIdNotNull(profileId);
        profileValidationManager.checkIfProfileExists(profileId);
        driverProfileRepo.deleteById(profileId);
        driverAccountRepo.deleteById(profileId);
    }

    @Transactional
    public RateDto setNewRate(RateDto rateDto) {
        profileValidationManager.checkIfProfileExists(rateDto.driverId());
        return RateMapper.converter.handleEntity(rateRepo.save(RateMapper.converter.handleDto(rateDto)));
    }

    @Transactional
    public void deleteRate(Long rateId) {
        rateValidationManager.checkRateExistsById(rateId);
        rateRepo.deleteById(rateId);
    }
}
