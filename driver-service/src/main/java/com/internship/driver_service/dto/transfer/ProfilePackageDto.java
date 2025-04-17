package com.internship.driver_service.dto.transfer;

import com.internship.driver_service.dto.response.ResponseProfileDto;
import lombok.Builder;

import java.util.List;

@Builder
public record ProfilePackageDto(
        List<ResponseProfileDto> profilesDto,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {
}
