package com.internship.ride_service.dto.transfer;

import com.internship.ride_service.dto.FareDto;

import java.util.List;

public record FarePackageDto(
        List<FareDto> fares,
        int totalCount
) {
}
