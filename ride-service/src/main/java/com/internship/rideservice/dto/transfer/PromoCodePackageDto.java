package com.internship.rideservice.dto.transfer;

import com.internship.rideservice.dto.response.ResponsePromoCodeDto;

import java.util.List;

public record PromoCodePackageDto(
        List<ResponsePromoCodeDto> promoCodeDtoList,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {
}