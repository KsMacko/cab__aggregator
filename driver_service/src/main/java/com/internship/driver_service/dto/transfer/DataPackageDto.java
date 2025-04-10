package com.internship.driver_service.dto.transfer;

import com.internship.driver_service.dto.ProfileDto;
import lombok.Builder;

import java.util.List;

@Builder
public record DataPackageDto(
        List<ProfileDto> profilesDto,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {
}
