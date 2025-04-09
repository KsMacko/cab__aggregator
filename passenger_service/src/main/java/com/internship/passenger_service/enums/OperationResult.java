package com.internship.passenger_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OperationResult {
    SUCCESS("success");
    private final String value;
}
