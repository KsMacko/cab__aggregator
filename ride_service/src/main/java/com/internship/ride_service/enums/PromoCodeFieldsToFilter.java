package com.internship.ride_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PromoCodeFieldsToFilter {
    CREATED_AT("createdAt"),
    VALID_UNTIL("validUntil");
    private final String fieldName;
}