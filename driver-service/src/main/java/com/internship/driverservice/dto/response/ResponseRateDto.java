package com.internship.driverservice.dto.response;

public record ResponseRateDto(
        Long id,
        Byte value,
        Long authorId,
        Long driverId
) {
}
