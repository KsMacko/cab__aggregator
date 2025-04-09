package com.internship.passenger_service.service;

import com.internship.passenger_service.dto.ProfileDto;
import com.internship.passenger_service.dto.RateDto;
import com.internship.passenger_service.dto.WrappedResult;
import com.internship.passenger_service.dto.mapper.ProfileMapper;
import com.internship.passenger_service.dto.mapper.RateMapper;
import com.internship.passenger_service.entity.PassengerAccount;
import com.internship.passenger_service.entity.PassengerProfile;
import com.internship.passenger_service.enums.OperationResult;
import com.internship.passenger_service.repo.PassengerAccountRepo;
import com.internship.passenger_service.repo.PassengerProfileRepo;
import com.internship.passenger_service.repo.RateRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandPassengerProfileService {
    private final PassengerProfileRepo passengerProfileRepo;
    private final PassengerAccountRepo passengerAccountRepo;
    private final RateRepo rateRepo;
    @Transactional
    public ProfileDto createNewPassengerProfile(ProfileDto profileDto) {
        if (profileDto.profileId() == null) {
            throw new RuntimeException("passenger.id.notNull");
        }
        PassengerAccount account = passengerAccountRepo.findById(profileDto.profileId())
                .orElseThrow(() -> new RuntimeException("passenger.account.notExists"));

        PassengerProfile passengerProfile = ProfileMapper.converter.handleDto(profileDto);
        passengerProfile.setProfileId(null);
        passengerProfile.setPassengerAccount(account);
        account.setPassengerProfile(passengerProfile);
        PassengerProfile savedProfile = passengerProfileRepo.save(passengerProfile);
        return ProfileMapper.converter.handleEntity(savedProfile);
    }
     @Transactional
     public ProfileDto updatePassengerProfile(ProfileDto profileDto) {
         if (profileDto.profileId() == null) {
             throw new RuntimeException("passenger.id.notNull");
         }
         PassengerProfile existingProfile = passengerProfileRepo.findById(profileDto.profileId())
                 .orElseThrow(() -> new RuntimeException("passenger.notFound"));
         ProfileMapper.converter.updateProfileFromDto(profileDto, existingProfile);

         return ProfileMapper.converter.handleEntity( passengerProfileRepo.save(existingProfile));
    }
    @Transactional
     public void deletePassengerProfile(Long profileId) {
        if (profileId == null) {
            throw new RuntimeException("passenger.id.notNull");
        }
        if(!passengerProfileRepo.existsById(profileId)){
            throw new RuntimeException("passenger.notFound");
        }
        passengerProfileRepo.deleteById(profileId);
        passengerAccountRepo.deleteById(profileId);
     }
     @Transactional
     public WrappedResult<String> setNewRate(RateDto rateDto){
         if(!passengerProfileRepo.existsById(rateDto.passengerId())){
             throw new RuntimeException("passenger.notFound");
         }
         rateRepo.save(RateMapper.converter.handleDto(rateDto));
         return new WrappedResult<String>(OperationResult.SUCCESS.getValue());
     }
     @Transactional
     public WrappedResult<String> deleteRate(Long rateId){
        if(!rateRepo.existsById(rateId))throw new RuntimeException("rate.notFound");
        rateRepo.deleteById(rateId);
        return new WrappedResult<>(OperationResult.SUCCESS.getValue());
     }
}
