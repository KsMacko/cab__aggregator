package com.internship.passengerservice.service;

import com.internship.commonevents.event.RideParticipantsConfirmation;
import com.internship.passengerservice.dto.mapper.RateMapper;
import com.internship.passengerservice.dto.request.RequestRateDto;
import com.internship.passengerservice.dto.response.ResponseRateDto;
import com.internship.passengerservice.entity.PassengerProfile;
import com.internship.passengerservice.entity.Rate;
import com.internship.passengerservice.repo.RateRepo;
import com.internship.passengerservice.service.communication.DriverFeignClient;
import com.internship.passengerservice.service.communication.RideFeignClient;
import com.internship.passengerservice.utils.ProfileValidationManager;
import com.internship.passengerservice.utils.RateValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RateService {
    private final RateRepo rateRepo;
    private final RateValidationManager rateValidationManager;
    private final ProfileValidationManager profileValidationManager;
    private final RideFeignClient rideFeignClient;
    private final DriverFeignClient driverFeignClient;
    private final RateMapper rateMapper;

    @Transactional
    public ResponseRateDto setNewRate(RequestRateDto rateDto) {
        PassengerProfile passengerProfile = profileValidationManager.getProfileByIdIfExists(rateDto.recipientId());
        RideParticipantsConfirmation participants = rideFeignClient.checkParticipants(rateDto.rideId());
        rateValidationManager.checkParticipants(participants, rateDto);
        Rate rate = rateMapper.handleDto(rateDto);
        rate.setPassenger(passengerProfile);
        return rateMapper.handleEntity(rateRepo.save(rate));
    }

    @Transactional
    public void deleteRate(Long driverId, Long rateId) {
        rateValidationManager.checkRateAuthor(driverId, rateId);
        rateRepo.deleteById(rateId);
    }
    public ResponseRateDto setRateToDriver(RequestRateDto rateDto) {
        return driverFeignClient.setRateToDriver(rateDto);
    }
    public void deleteRateFromDriver(Long passengerId, Long rateId) {
        driverFeignClient.deleteRateFromDriver(passengerId, rateId);
    }
}
