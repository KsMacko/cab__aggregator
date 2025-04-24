package com.internship.rideservice.util.validators;

import com.internship.rideservice.entity.PromoCode;
import com.internship.rideservice.repo.PromoCodeRepo;
import com.internship.rideservice.util.exceptions.InvalidInputException;
import com.internship.rideservice.util.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.internship.rideservice.util.exceptions.ExceptionCodes.NO_CURRENT_PROMO_CODE;
import static com.internship.rideservice.util.exceptions.ExceptionCodes.PROMO_CODE_NOT_FOUND;
import static com.internship.rideservice.util.exceptions.ExceptionCodes.PROMO_CODE_STILL_VALID;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class PromoCodeValidationManager {
    private final PromoCodeRepo promoCodeRepo;


    public void checkIfExistsById(String id) {
        if (!promoCodeRepo.existsById(id)) {
            throw new ResourceNotFoundException(PROMO_CODE_NOT_FOUND.getCode());
        }
    }

    public PromoCode getPromoCodeByIdIfExists(String id) {
        return promoCodeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(PROMO_CODE_NOT_FOUND.getCode()));
    }

    public void checkForCreationPromoCodeValidity(String promoCode) {
        Optional<PromoCode> existingCode = promoCodeRepo.findByPromoCodeAndValidUntilAfter(
                promoCode,
                LocalDateTime.now());
        if (existingCode.isPresent()) {
            throw new InvalidInputException(PROMO_CODE_STILL_VALID.getCode());
        }
    }

    public PromoCode getCurrentPromoCode(String promoCode) {
        if (nonNull(promoCode)) {
            Optional<PromoCode> code = promoCodeRepo.findByPromoCodeAndValidUntilAfter(promoCode, LocalDateTime.now());
            if (code.isPresent()) {
                return code.get();
            } else {
                throw new ResourceNotFoundException(NO_CURRENT_PROMO_CODE.getCode());
            }
        }
        return null;
    }
}
