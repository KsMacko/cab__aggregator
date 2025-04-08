package com.internship.passenger_service.dto.transfer_objects;


import com.internship.passenger_service.dto.ProfileDto;

import java.util.List;

public record DataPackageDto(
        List<ProfileDto> profiles,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {}
