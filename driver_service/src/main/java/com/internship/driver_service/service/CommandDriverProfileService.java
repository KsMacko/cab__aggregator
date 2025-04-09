package com.internship.driver_service.service;

import com.internship.driver_service.dto.ProfileDto;
import com.internship.driver_service.dto.RateDto;
import com.internship.driver_service.dto.WrappedResponse;
import com.internship.driver_service.dto.mapper.ProfileMapper;
import com.internship.driver_service.dto.mapper.RateMapper;
import com.internship.driver_service.entity.DriverAccount;
import com.internship.driver_service.entity.DriverProfile;
import com.internship.driver_service.enums.OperationResult;
import com.internship.driver_service.repo.DriverAccountRepo;
import com.internship.driver_service.repo.DriverProfileRepo;
import com.internship.driver_service.repo.RateRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandDriverProfileService {
    private final DriverProfileRepo driverProfileRepo;
    private final DriverAccountRepo driverAccountRepo;
    private final RateRepo rateRepo;

    @Transactional
    public ProfileDto createProfile(ProfileDto profileDto) {
        DriverAccount driverAccount = driverAccountRepo.findById(profileDto.profileId())
                .orElseThrow(()->new RuntimeException("driver.account.notExists"));
        DriverProfile driverProfile = ProfileMapper.converter.handleDto(profileDto);
        driverProfile.setDriverAccount(driverAccount);
        driverProfile.setProfileId(null);
        driverAccount.setDriverProfile(driverProfile);
        return ProfileMapper.converter.handleEntity(driverProfileRepo.save(driverProfile));
    }
    @Transactional
    public ProfileDto updateDriverProfile(ProfileDto profileDto) {
        if (profileDto.profileId() == null) {
            throw new RuntimeException("driver.id.notNull");
        }
        DriverProfile existingProfile = driverProfileRepo
                .findById(profileDto.profileId())
                .orElseThrow(() -> new RuntimeException("driver.notFound"));
        ProfileMapper.converter.updateProfileFromDto(profileDto, existingProfile);

        return ProfileMapper.converter.handleEntity( driverProfileRepo.save(existingProfile));
    }
    @Transactional
    public void deleteDriverProfile(Long profileId) {
        if(profileId == null)
            throw new RuntimeException("driver.id.notNull");
        if(!driverProfileRepo.existsById(profileId))
            throw new RuntimeException("driver.notFound");
        driverProfileRepo.deleteById(profileId);
        driverAccountRepo.deleteById(profileId);
    }
    @Transactional
    public WrappedResponse<String> setNewRate(RateDto rateDto){
        if(!driverProfileRepo.existsById(rateDto.driverId())){
            throw new RuntimeException("driver.notFound");
        }
        rateRepo.save(RateMapper.converter.handleDto(rateDto));
        return new WrappedResponse<>(OperationResult.SUCCESS.getValue());
    }
    @Transactional
    public WrappedResponse<String> deleteRate(Long rateId){
        if(!rateRepo.existsById(rateId))throw new RuntimeException("rate.notFound");
        rateRepo.deleteById(rateId);
        return new WrappedResponse<>(OperationResult.SUCCESS.getValue());
    }


}
