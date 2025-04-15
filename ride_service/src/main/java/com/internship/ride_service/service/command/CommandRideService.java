package com.internship.ride_service.service.command;

import com.internship.ride_service.dto.RideDto;
import com.internship.ride_service.dto.mapper.RideMapper;
import com.internship.ride_service.entity.Ride;
import com.internship.ride_service.enums.RideStatus;
import com.internship.ride_service.repo.RideRepo;
import com.internship.ride_service.util.RideValidationManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetTime;

@Service
@RequiredArgsConstructor
public class CommandRideService {

    private final RideRepo rideRepo;
    private final CalculatePriceService calculatePriceService;
    private final RideValidationManager rideValidationManager;
    private final RideMapper rideMapper;

    @Transactional
    public RideDto createRide(RideDto rideDto) {
        Ride rideEntity = rideMapper.handleDto(rideDto);
        return rideMapper.handleEntity(rideRepo.save(rideEntity));
    }

    @Transactional
    public RideDto updateRide(RideDto updatedRideDto) {
        Ride existingRide = rideValidationManager.getRideByIdIfExists(updatedRideDto.id());
        rideMapper.updateEntity(updatedRideDto, existingRide);
        return rideMapper.handleEntity(rideRepo.save(existingRide));
    }

    @Transactional
    public void deleteRide(String rideId) {
        rideValidationManager.checkIfExistsById(rideId);
        rideRepo.deleteById(rideId);
    }

    @Transactional
    public RideDto changeRideStatusToAccepted(String rideId, Long driverId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        ride.setDriverId(driverId);
        return saveRideAndGetStatus(ride, RideStatus.ACCEPTED);
    }

    @Transactional
    public RideDto changeRideStatusToWaitingForPassenger(String rideId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        ride.setStartWaitingTime(OffsetTime.now());
        return saveRideAndGetStatus(ride, RideStatus.WAIT_FOR_PASSENGER);
    }

    @Transactional
    public RideDto changeRideStatusToInProgress(String rideId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        ride.setStartTime(OffsetTime.now());
        return saveRideAndGetStatus(ride, RideStatus.IN_PROGRESS);
    }

    @Transactional
    public RideDto changeRideStatusRecalculated(String rideId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        ride.setEndTime(OffsetTime.now());
        BigDecimal recalculatedPrice = calculatePriceService.ReCalculatePrice(ride);
        ride.setPrice(recalculatedPrice);
        return saveRideAndGetStatus(ride, RideStatus.RECALCULATED);
    }

    @Transactional
    public RideDto changeRideStatusToCompleted(String rideId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        return saveRideAndGetStatus(ride, RideStatus.COMPLETED);
    }

    private RideDto saveRideAndGetStatus(Ride ride, RideStatus rideStatus) {
        ride.setStatus(rideStatus);
        return rideMapper.handleEntity(rideRepo.save(ride));
    }
}
