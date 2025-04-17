package com.internship.rideservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.internship.rideservice.entity.PromoCode.Fields.createdAt;
import static com.internship.rideservice.entity.PromoCode.Fields.validUntil;

@Getter
@AllArgsConstructor
public enum PromoCodeFieldsToFilter {
    CREATED_AT(createdAt),
    VALID_UNTIL(validUntil);
    private final String fieldName;
}