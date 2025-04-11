package com.internship.ride_service.service.command;

import com.internship.ride_service.dto.PromoCodeDto;
import com.internship.ride_service.dto.mapper.PromoCodeMapper;
import com.internship.ride_service.entity.PromoCode;
import com.internship.ride_service.repo.PromoCodeRepo;
import com.internship.ride_service.util.PromoCodeValidationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommandPromoCodeService {
    private final PromoCodeRepo promoCodeRepo;
    private final PromoCodeValidationManager promoCodeValidationManager;

    @Transactional
    public PromoCodeDto createPromoCode(PromoCodeDto promoCodeDto) {
        promoCodeValidationManager.checkForCreationPromoCodeValidity(promoCodeDto.id(), promoCodeDto.promoCode());
        PromoCode promoCode = PromoCodeMapper.converter.handleDto(promoCodeDto);
        return PromoCodeMapper.converter.handleEntity(promoCodeRepo.save(promoCode));
    }

    @Transactional
    public void deletePromoCode(String id) {
        promoCodeValidationManager.checkIfExistsById(id);
        promoCodeRepo.deleteById(id);
    }

    @Transactional
    public PromoCodeDto updatePromoCode(PromoCodeDto promoCodeDto) {
        PromoCode existingPromoCode = promoCodeValidationManager.getPromoCodeByIdIfExists(promoCodeDto.id());
        PromoCodeMapper.converter.updateEntity(promoCodeDto, existingPromoCode);
        return PromoCodeMapper.converter.handleEntity(promoCodeRepo.save(existingPromoCode));
    }
}
