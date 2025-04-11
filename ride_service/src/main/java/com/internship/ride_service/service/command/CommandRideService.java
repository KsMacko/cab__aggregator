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

    @Transactional
    public RideDto createRide(RideDto rideDto) {
        Ride rideEntity = RideMapper.converter.handleDto(rideDto);
        return RideMapper.converter.handleEntity(rideRepo.save(rideEntity));
    }

    @Transactional
    public RideDto updateRide(RideDto updatedRideDto) {
        Ride existingRide = rideValidationManager.getRideByIdIfExists(updatedRideDto.id());
        RideMapper.converter.updateEntity(updatedRideDto, existingRide);
        return RideMapper.converter.handleEntity(rideRepo.save(existingRide));
    }

    @Transactional
    public void deleteRide(String rideId) {
        rideValidationManager.checkIfExistsById(rideId);
        rideRepo.deleteById(rideId);
    }

    @Transactional
    public RideStatus changeRideStatusToAccepted(String rideId, Long driverId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        ride.setDriverId(driverId);
        return saveRideAndGetStatus(ride, RideStatus.ACCEPTED);
    }

    @Transactional
    public RideStatus changeRideStatusToWaitingForPassenger(String rideId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        ride.setStartWaitingTime(OffsetTime.now());
        return saveRideAndGetStatus(ride, RideStatus.WAIT_FOR_PASSENGER);
    }

    @Transactional
    public RideStatus changeRideStatusToInProgress(String rideId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        ride.setStartTime(OffsetTime.now());
        return saveRideAndGetStatus(ride, RideStatus.IN_PROGRESS);
    }

    @Transactional
    public BigDecimal changeRideStatusRecalculated(String rideId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        ride.setEndTime(OffsetTime.now());
        BigDecimal recalculatedPrice = calculatePriceService.ReCalculatePrice(ride);
        ride.setPrice(recalculatedPrice);
        saveRideAndGetStatus(ride, RideStatus.RECALCULATED);
        return recalculatedPrice;
    }

    @Transactional
    public RideStatus changeRideStatusToCompleted(String rideId) {
        Ride ride = rideValidationManager.getRideByIdIfExists(rideId);
        return saveRideAndGetStatus(ride, RideStatus.COMPLETED);
    }

    public RideStatus saveRideAndGetStatus(Ride ride, RideStatus rideStatus) {
        ride.setStatus(rideStatus);
        rideRepo.save(ride);
        return rideStatus;
    }
}
