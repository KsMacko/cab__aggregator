package com.internship.ride_service.dto.transfer_objects;

import com.internship.ride_service.dto.PromoCodeDto;

import java.util.List;

public record PromoCodePackageDto(
    List<PromoCodeDto> promoCodeDtoList,
    long totalElements,
    int pageNumber,
    int pageSize,
    int totalPages
){}