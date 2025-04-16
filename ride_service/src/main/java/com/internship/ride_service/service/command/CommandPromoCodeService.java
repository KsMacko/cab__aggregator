package com.internship.ride_service.service.command;

import com.internship.ride_service.dto.PromoCodeDto;
import com.internship.ride_service.dto.mapper.PromoCodeMapper;
import com.internship.ride_service.entity.PromoCode;
import com.internship.ride_service.repo.PromoCodeRepo;
import com.internship.ride_service.util.validators.PromoCodeValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class CommandPromoCodeService {
    private final PromoCodeRepo promoCodeRepo;
    private final PromoCodeValidationManager promoCodeValidationManager;
    private final PromoCodeMapper promoCodeMapper;

    @Transactional
    public PromoCodeDto createPromoCode(PromoCodeDto promoCodeDto) {
        promoCodeValidationManager.checkForCreationPromoCodeValidity(promoCodeDto.promoCode());
        PromoCode promoCode = promoCodeMapper.handleDto(promoCodeDto);
        promoCode.setValidUntil(OffsetDateTime.parse(promoCodeDto.validUntil()));
        return promoCodeMapper.handleEntity(promoCodeRepo.save(promoCode));
    }

    @Transactional
    public void deletePromoCode(String id) {
        promoCodeValidationManager.checkIfExistsById(id);
        promoCodeRepo.deleteById(id);
    }

    @Transactional
    public PromoCodeDto updatePromoCode(PromoCodeDto promoCodeDto) {
        PromoCode existingPromoCode = promoCodeValidationManager.getPromoCodeByIdIfExists(promoCodeDto.id());
        promoCodeMapper.updateEntity(promoCodeDto, existingPromoCode);
        return promoCodeMapper.handleEntity(promoCodeRepo.save(existingPromoCode));
    }
}
