package com.internship.ride_service.dto.transfer;

import com.internship.ride_service.dto.response.ResponseRideDto;

import java.util.List;

public record RidePackageDto(
        List<ResponseRideDto> ridesDto,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {
}
