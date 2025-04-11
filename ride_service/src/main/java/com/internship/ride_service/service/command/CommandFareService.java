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

    @Transactional
    public FareDto createFare(FareDto fareDto) {
        fareValidationManager.checkForDuplicateType(fareDto.type());
        Fare fare = FareMapper.converter.handleDto(fareDto);
        Fare savedFare = fareRepo.save(fare);
        return FareMapper.converter.handleEntity(savedFare);
    }

    @Transactional
    public void deleteFare(FareType type) {
        fareValidationManager.checkIfNotExistsByType(type);
        fareRepo.deleteFareByType(type);
    }

    @Transactional
    public FareDto updateFare(FareDto fareDto) {
        Fare existingFare = fareValidationManager.getFareIfExists(fareDto.type());
        FareMapper.converter.updateEntity(fareDto, existingFare);
        Fare updatedFare = fareRepo.save(existingFare);
        return FareMapper.converter.handleEntity(updatedFare);
    }
}
