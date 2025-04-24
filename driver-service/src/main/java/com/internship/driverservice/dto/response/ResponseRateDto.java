package com.internship.driverservice.dto.response;

import java.time.LocalDateTime;

public record ResponseRateDto(
        Long id,
        Byte value,
        Long authorId,
        Long recipientId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
