package com.internship.rideservice.dto.transfer;

import com.internship.rideservice.dto.response.ResponseRideDto;

import java.util.List;

public record RidePackageDto(
        List<ResponseRideDto> ridesDto,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {
}
