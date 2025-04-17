package com.internship.ride_service.service.command;

import com.internship.ride_service.dto.request.RequestRideDto;
import com.internship.ride_service.dto.response.ResponseRideDto;
import com.internship.ride_service.dto.mapper.RideMapper;
import com.internship.ride_service.entity.Ride;
import com.internship.ride_service.enums.RideStatus;
import com.internship.ride_service.repo.RideRepo;
import com.internship.ride_service.util.validators.RideValidationManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.OffsetTime;

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
