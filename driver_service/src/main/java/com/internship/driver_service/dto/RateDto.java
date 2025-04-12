package com.internship.driver_service.dto;

public record RateDto(
        Byte value,
        Long authorId,
        Long driverId
) {
}
