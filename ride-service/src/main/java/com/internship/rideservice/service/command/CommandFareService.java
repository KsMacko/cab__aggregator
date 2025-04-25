package com.internship.rideservice.service.command;

import com.internship.rideservice.dto.request.RequestFareDto;
import com.internship.rideservice.dto.response.ResponseFareDto;
import com.internship.rideservice.dto.mapper.FareMapper;
import com.internship.rideservice.entity.Fare;
import com.internship.rideservice.enums.FareType;
import com.internship.rideservice.repo.FareRepo;
import com.internship.rideservice.util.validators.FareValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommandFareService {
    private final FareRepo fareRepo;
    private final FareValidationManager fareValidationManager;
    private final FareMapper fareMapper;

    @Transactional
    public Fare createFare(RequestFareDto fareDto) {
        fareValidationManager.checkForDuplicateType(FareType.valueOf(fareDto.type()));
        return fareRepo.save(fareMapper.handleDto(fareDto));
    }

    @Transactional
    public void deleteFare(FareType type) {
        fareValidationManager.checkIfNotExistsByType(type);
        fareRepo.deleteFareByType(type);
    }

    @Transactional
    public Fare updateFare(RequestFareDto fareDto) {
        Fare existingFare = fareValidationManager.getFareIfExists(FareType.valueOf(fareDto.type()));
        fareMapper.updateEntity(fareDto, existingFare);
        return fareRepo.save(existingFare);
    }
}
