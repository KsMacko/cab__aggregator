package com.internship.driver_service.dto.response;

public record ResponseCarDto(
        Long id,
        Long driverId,
        Boolean isCurrent,
        String carNumber,
        String brand,
        String color
) {
}