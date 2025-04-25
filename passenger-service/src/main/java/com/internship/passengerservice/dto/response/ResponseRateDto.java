package com.internship.passengerservice.dto.response;

import java.time.LocalDateTime;

public record ResponseRateDto(
        Byte value,
        Long authorId,
        Long recipientId,
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
