package com.internship.passenger_service.dto;

public record RateDto(
        Byte value,
        Long authorId,
        Long passengerId) {
}
