package com.internship.passenger_service.dto.transfer_objects;


import com.internship.passenger_service.dto.ProfileDto;
import lombok.Builder;

import java.util.List;

@Builder
public record DataPackageDto(
        List<ProfileDto> profiles,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {}
