package com.internship.driver_service.dto.response;

public record ResponseRateDto(
        Long id,
        Byte value,
        Long authorId,
        Long driverId
) {
}
