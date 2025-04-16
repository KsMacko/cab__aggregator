package com.internship.ride_service.util.validators;

import com.internship.ride_service.entity.PromoCode;
import com.internship.ride_service.repo.PromoCodeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Optional;

import static com.internship.ride_service.util.exceptions.ExceptionCodes.NO_CURRENT_PROMO_CODE;
import static com.internship.ride_service.util.exceptions.ExceptionCodes.PROMO_CODE_NOT_FOUND;
import static com.internship.ride_service.util.exceptions.ExceptionCodes.PROMO_CODE_STILL_VALID;

@Component
@RequiredArgsConstructor
public class PromoCodeValidationManager {
    private final PromoCodeRepo promoCodeRepo;

    public void checkIfExistsById(String id) {
        if (!promoCodeRepo.existsById(id)) {
            throw new RuntimeException(PROMO_CODE_NOT_FOUND.getCode());
        }
    }

    public PromoCode getPromoCodeByIdIfExists(String id) {
        return promoCodeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(PROMO_CODE_NOT_FOUND.getCode()));
    }

    public void checkForCreationPromoCodeValidity(String promoCode) {
        Optional<PromoCode> existingCode = promoCodeRepo.findByPromoCodeAndValidUntilAfter(
                promoCode,
                OffsetDateTime.now());
        if (existingCode.isPresent()) {
            throw new RuntimeException(PROMO_CODE_STILL_VALID.getCode());
        }
    }

    public PromoCode getCurrentPromoCode(String promoCode) {
        if (!promoCode.isEmpty()) {
            Optional<PromoCode> code = promoCodeRepo.findByPromoCodeAndValidUntilAfter(promoCode, OffsetDateTime.now());
            if (code.isPresent()) {
                return code.get();
            } else {
                throw new RuntimeException(NO_CURRENT_PROMO_CODE.getCode());
            }
        }
        return null;
    }
}
