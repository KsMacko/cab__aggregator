package com.internship.ride_service.dto.transfer;

import com.internship.ride_service.dto.response.ResponsePromoCodeDto;

import java.util.List;

public record PromoCodePackageDto(
        List<ResponsePromoCodeDto> promoCodeDtoList,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {
}