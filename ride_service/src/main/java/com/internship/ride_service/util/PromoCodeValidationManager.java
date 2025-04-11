package com.internship.ride_service.util;

import com.internship.ride_service.entity.PromoCode;
import com.internship.ride_service.repo.PromoCodeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class PromoCodeValidationManager {
    private final PromoCodeRepo promoCodeRepo;

    public void checkIfExistsById(String id) {
        if (!promoCodeRepo.existsById(id)) {
            throw new RuntimeException("promo.code.notFound");
        }
    }

    public PromoCode getPromoCodeByIdIfExists(String id) {
        return promoCodeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("promo.code.notFound"));
    }

    public void checkForCreationPromoCodeValidity(String id, String promoCode) {
        if (nonNull(id)) throw new RuntimeException("promo.code.id.Null");
        Optional<PromoCode> existingCode = promoCodeRepo.findByPromoCodeAndValidUntilAfter(
                promoCode,
                OffsetDateTime.now());
        if (existingCode.isPresent())
            throw new RuntimeException("promo.code.stillValid");
    }

    public PromoCode getCurrentPromoCode(String promoCode) {
        if (!promoCode.isEmpty()) {
            Optional<PromoCode> code = promoCodeRepo.findByPromoCodeAndValidUntilAfter(promoCode, OffsetDateTime.now());
            if (code.isPresent()) return code.get();
            else throw new RuntimeException("promo.code.noCurrentCode");
        }
        return null;
    }
}
