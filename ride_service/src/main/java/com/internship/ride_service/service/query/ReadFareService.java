package com.internship.ride_service.service.query;


import com.internship.ride_service.dto.FareDto;
import com.internship.ride_service.dto.mapper.FareMapper;
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

    @Transactional(readOnly = true)
    public List<FareDto> getAllFares() {
        return fareRepo.findAll().stream()
                .map(FareMapper.converter::handleEntity)
                .toList();
    }

    @Transactional(readOnly = true)
    public FareDto getFareById(FareType type) {
        Fare fare = fareValidationManager.getFareIfExists(type);
        return FareMapper.converter.handleEntity(fare);
    }

}