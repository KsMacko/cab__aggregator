package com.internship.driver_service.dto;

import jakarta.persistence.Column;

public record RateDto(
        Byte value,
        Long authorId,
        Long driverId
) { }
