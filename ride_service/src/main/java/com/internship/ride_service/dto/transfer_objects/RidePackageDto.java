package com.internship.ride_service.dto.transfer_objects;

import com.internship.ride_service.dto.RideDto;

import java.util.List;

public record RidePackageDto (
    List<RideDto> ridesDto,
    long totalElements,
    int pageNumber,
    int pageSize,
    int totalPages
    ){}
