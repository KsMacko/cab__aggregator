package com.internship.passengerservice.dto.response;

import java.time.LocalDateTime;

public record ResponseRateDto(
        Byte value,
        Long authorId,
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
