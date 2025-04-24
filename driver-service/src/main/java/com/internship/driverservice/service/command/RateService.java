package com.internship.driverservice.service.command;

import com.internship.commonevents.event.RideParticipantsConfirmation;
import com.internship.driverservice.dto.mapper.RateMapper;
import com.internship.driverservice.dto.request.RequestRateDto;
import com.internship.driverservice.dto.response.ResponseRateDto;
import com.internship.driverservice.entity.DriverProfile;
import com.internship.driverservice.entity.Rate;
import com.internship.driverservice.repo.RateRepo;
import com.internship.driverservice.service.communication.PassengerFeignClient;
import com.internship.driverservice.service.communication.RideFeignClient;
import com.internship.driverservice.utils.validation.ProfileValidationManager;
import com.internship.driverservice.utils.validation.RateValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RateService {
    private final RateRepo rateRepo;
    private final RateValidationManager rateValidationManager;
    private final ProfileValidationManager profileValidationManager;
    private final RateMapper rateMapper;
    private final RideFeignClient rideFeignClient;
    private final PassengerFeignClient passengerFeignClient;

    @Transactional
    public ResponseRateDto setNewRate(RequestRateDto rateDto) {
        DriverProfile driverProfile = profileValidationManager.getDriverProfile(rateDto.recipientId());
        RideParticipantsConfirmation participants = rideFeignClient.checkParticipants(rateDto.rideId());
        rateValidationManager.checkParticipants(participants, rateDto);
        Rate rate = rateMapper.handleDto(rateDto);
        rate.setDriver(driverProfile);
        return rateMapper.handleEntity(rateRepo.save(rate));
    }

    @Transactional
    public void deleteRate(Long passengerId, Long rateId) {
        rateValidationManager.checkRateAuthor(passengerId, rateId);
        rateRepo.deleteById(rateId);
    }
    public ResponseRateDto setRateToPassenger(RequestRateDto rateDto) {
        return passengerFeignClient.setRateToPassenger(rateDto);
    }
    public void deleteRateFromPassenger(Long driverId, Long rateId) {
        passengerFeignClient.deleteRateFromPassenger(driverId, rateId);
    }
}
