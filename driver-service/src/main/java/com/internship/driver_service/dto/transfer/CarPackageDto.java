package com.internship.driver_service.dto.transfer;

import com.internship.driver_service.dto.response.ResponseCarDto;
import lombok.Builder;

import java.util.List;

@Builder
public record CarPackageDto(
        List<ResponseCarDto> carsDto,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {
}
