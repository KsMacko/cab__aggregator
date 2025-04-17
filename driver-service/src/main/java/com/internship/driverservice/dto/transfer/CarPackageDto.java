package com.internship.driverservice.dto.transfer;

import com.internship.driverservice.dto.response.ResponseCarDto;
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
