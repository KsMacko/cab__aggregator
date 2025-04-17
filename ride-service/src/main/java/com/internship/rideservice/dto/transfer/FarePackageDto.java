package com.internship.rideservice.dto.transfer;

import com.internship.rideservice.dto.response.ResponseFareDto;

import java.util.List;

public record FarePackageDto(
        List<ResponseFareDto> fares,
        int totalCount
) {
}
