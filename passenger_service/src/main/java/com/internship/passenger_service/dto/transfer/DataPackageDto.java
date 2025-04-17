package com.internship.passenger_service.dto.transfer;


import com.internship.passenger_service.dto.response.ResponseProfileDto;
import lombok.Builder;

import java.util.List;

@Builder
public record DataPackageDto(
        List<ResponseProfileDto> profiles,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {
}
