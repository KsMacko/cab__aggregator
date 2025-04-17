package com.internship.rideservice.service.command;

import com.internship.rideservice.dto.request.RequestPromoCodeDto;
import com.internship.rideservice.dto.response.ResponsePromoCodeDto;
import com.internship.rideservice.dto.mapper.PromoCodeMapper;
import com.internship.rideservice.entity.PromoCode;
import com.internship.rideservice.repo.PromoCodeRepo;
import com.internship.rideservice.util.validators.PromoCodeValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommandPromoCodeService {
    private final PromoCodeRepo promoCodeRepo;
    private final PromoCodeValidationManager promoCodeValidationManager;
    private final PromoCodeMapper promoCodeMapper;

    @Transactional
    public ResponsePromoCodeDto createPromoCode(RequestPromoCodeDto promoCodeDto) {
        promoCodeValidationManager.checkForCreationPromoCodeValidity(promoCodeDto.promoCode());
        PromoCode promoCode = promoCodeMapper.handleDto(promoCodeDto);
        promoCode.setValidUntil(LocalDateTime.parse(promoCodeDto.validUntil()));
        return promoCodeMapper.handleEntity(promoCodeRepo.save(promoCode));
    }

    @Transactional
    public void deletePromoCode(String id) {
        promoCodeValidationManager.checkIfExistsById(id);
        promoCodeRepo.deleteById(id);
    }

    @Transactional
    public ResponsePromoCodeDto updatePromoCode(String promoCodeId, RequestPromoCodeDto promoCodeDto) {
        PromoCode existingPromoCode = promoCodeValidationManager.getPromoCodeByIdIfExists(promoCodeId);
        promoCodeMapper.updateEntity(promoCodeDto, existingPromoCode);
        return promoCodeMapper.handleEntity(promoCodeRepo.save(existingPromoCode));
    }
}
