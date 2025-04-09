package com.internship.driver_service.dto;

public record CarDto(
        Long id,
        String carNumber,
        String brand,
        String color
) { }
