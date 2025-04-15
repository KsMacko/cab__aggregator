package com.internship.ride_service.service.query;

import com.internship.ride_service.dto.FareDto;
import com.internship.ride_service.dto.mapper.FareMapper;
import com.internship.ride_service.dto.transfer.FarePackageDto;
import com.internship.ride_service.entity.Fare;
import com.internship.ride_service.enums.FareType;
import com.internship.ride_service.repo.FareRepo;
import com.internship.ride_service.util.FareValidationManager;
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
        List<FareDto> fares = fareRepo.findAll().stream()
                .map(fareMapper::handleEntity)
                .toList();
        return new FarePackageDto(
                fares,
                fares.size()
        );
    }

    @Transactional(readOnly = true)
    public FareDto getFareById(FareType type) {
        Fare fare = fareValidationManager.getFareIfExists(type);
        return fareMapper.handleEntity(fare);
    }

}