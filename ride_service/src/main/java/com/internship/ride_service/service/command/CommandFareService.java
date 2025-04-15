package com.internship.ride_service.service.command;

import com.internship.ride_service.dto.FareDto;
import com.internship.ride_service.dto.mapper.FareMapper;
import com.internship.ride_service.entity.Fare;
import com.internship.ride_service.enums.FareType;
import com.internship.ride_service.repo.FareRepo;
import com.internship.ride_service.util.FareValidationManager;
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
    public FareDto createFare(FareDto fareDto) {
        fareValidationManager.checkForDuplicateType(fareDto.type());
        Fare fare = fareMapper.handleDto(fareDto);
        Fare savedFare = fareRepo.save(fare);
        return fareMapper.handleEntity(savedFare);
    }

    @Transactional
    public void deleteFare(FareType type) {
        fareValidationManager.checkIfNotExistsByType(type);
        fareRepo.deleteFareByType(type);
    }

    @Transactional
    public FareDto updateFare(FareDto fareDto) {
        Fare existingFare = fareValidationManager.getFareIfExists(fareDto.type());
        fareMapper.updateEntity(fareDto, existingFare);
        Fare updatedFare = fareRepo.save(existingFare);
        return fareMapper.handleEntity(updatedFare);
    }
}
