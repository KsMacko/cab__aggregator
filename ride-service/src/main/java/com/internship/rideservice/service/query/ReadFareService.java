package com.internship.rideservice.service.query;

import com.internship.rideservice.dto.response.ResponseFareDto;
import com.internship.rideservice.dto.mapper.FareMapper;
import com.internship.rideservice.dto.transfer.FarePackageDto;
import com.internship.rideservice.entity.Fare;
import com.internship.rideservice.enums.FareType;
import com.internship.rideservice.repo.FareRepo;
import com.internship.rideservice.util.validators.FareValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadFareService {
    private final FareRepo fareRepo;
    private final FareValidationManager fareValidationManager;
    private final FareMapper fareMapper;

    @Transactional(readOnly = true)
    public FarePackageDto getAllFares() {
        List<ResponseFareDto> fares = fareRepo.findAll().stream()
                .map(fareMapper::handleEntity)
                .toList();
        return new FarePackageDto(
                fares,
                fares.size()
        );
    }

    @Transactional(readOnly = true)
    public ResponseFareDto getFareById(FareType type) {
        Fare fare = fareValidationManager.getFareIfExists(type);
        return fareMapper.handleEntity(fare);
    }

}