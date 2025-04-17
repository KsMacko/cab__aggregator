package com.internship.ride_service.service.command;

import com.internship.ride_service.dto.request.RequestFareDto;
import com.internship.ride_service.dto.request.RequestPromoCodeDto;
import com.internship.ride_service.dto.response.ResponseFareDto;
import com.internship.ride_service.dto.mapper.FareMapper;
import com.internship.ride_service.entity.Fare;
import com.internship.ride_service.enums.FareType;
import com.internship.ride_service.repo.FareRepo;
import com.internship.ride_service.util.validators.FareValidationManager;
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
    public ResponseFareDto createFare(RequestFareDto fareDto) {
        fareValidationManager.checkForDuplicateType(FareType.valueOf(fareDto.type()));
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
    public ResponseFareDto updateFare(RequestFareDto fareDto) {
        Fare existingFare = fareValidationManager.getFareIfExists(FareType.valueOf(fareDto.type()));
        fareMapper.updateEntity(fareDto, existingFare);
        Fare updatedFare = fareRepo.save(existingFare);
        return fareMapper.handleEntity(updatedFare);
    }
}
