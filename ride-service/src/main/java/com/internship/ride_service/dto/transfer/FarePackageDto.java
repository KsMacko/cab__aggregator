package com.internship.ride_service.dto.transfer;

import com.internship.ride_service.dto.response.ResponseFareDto;

import java.util.List;

public record FarePackageDto(
        List<ResponseFareDto> fares,
        int totalCount
) {
}
