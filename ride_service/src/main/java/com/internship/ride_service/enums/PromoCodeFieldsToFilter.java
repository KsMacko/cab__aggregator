package com.internship.ride_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.internship.ride_service.entity.PromoCode.Fields.*;

@Getter
@AllArgsConstructor
public enum PromoCodeFieldsToFilter {
    CREATED_AT(createdAt),
    VALID_UNTIL(validUntil);
    private final String fieldName;
}