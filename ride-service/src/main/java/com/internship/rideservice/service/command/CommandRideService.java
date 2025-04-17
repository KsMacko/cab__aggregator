package com.internship.rideservice.service.command;

import com.internship.rideservice.dto.request.RequestRideDto;
import com.internship.rideservice.dto.response.ResponseRideDto;
import com.internship.rideservice.dto.mapper.RideMapper;
import com.internship.rideservice.entity.Ride;
import com.internship.rideservice.enums.RideStatus;
import com.internship.rideservice.repo.RideRepo;
import com.internship.rideservice.util.validators.RideValidationManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class CommandRideService {

    private final RideRepo rideRepo;
    private final CalculatePriceService calculatePriceService;
    private final RideValidationManager rideValidationManager;
    private final RideMapper rideMapper;

    @Transactional
    public ResponseRideDto createRide(RequestRideDto rideDto) {
        Ride rideEntity = rideMapper.handleDto(rideDto);
        return rideMapper.handleEntity(rideRepo.save(rideEntity));
    }

    @Transactional
    public void deleteRide(String rideId) {
        rideValidationManager.checkIfExistsById(rideId);
        rideRepo.deleteById(rideId);
    }

    @Transactional
    public ResponseRideDto changeRideStatusToAccepted(String rideId, Long driverId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        ride.setDriverId(driverId);
        return saveRideAndGetStatus(ride, RideStatus.ACCEPTED);
    }

    @Transactional
    public ResponseRideDto changeRideStatusToWaitingForPassenger(String rideId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        ride.setStartWaitingTime(LocalTime.now());
        return saveRideAndGetStatus(ride, RideStatus.WAIT_FOR_PASSENGER);
    }

    @Transactional
    public ResponseRideDto changeRideStatusToInProgress(String rideId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        ride.setStartTime(LocalTime.now());
        return saveRideAndGetStatus(ride, RideStatus.IN_PROGRESS);
    }

    @Transactional
    public ResponseRideDto changeRideStatusRecalculated(String rideId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        ride.setEndTime(LocalTime.now());
        BigDecimal recalculatedPrice = calculatePriceService.ReCalculatePrice(ride);
        ride.setPrice(recalculatedPrice);
        return saveRideAndGetStatus(ride, RideStatus.RECALCULATED);
    }

    @Transactional
    public ResponseRideDto changeRideStatusToCompleted(String rideId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        return saveRideAndGetStatus(ride, RideStatus.COMPLETED);
    }

    private ResponseRideDto saveRideAndGetStatus(Ride ride, RideStatus rideStatus) {
        ride.setStatus(rideStatus);
        return rideMapper.handleEntity(rideRepo.save(ride));
    }
}
