package com.internship.driver_service.dto.transfer;

import com.internship.driver_service.dto.CarDto;
import lombok.Builder;

import java.util.List;

@Builder
public record CarPackageDto(
        List<CarDto> carsDto,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {
}
