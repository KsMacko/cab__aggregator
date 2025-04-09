package com.internship.driver_service.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record DataPackageDto (
        List<ProfileDto> profilesDto,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
){ }
