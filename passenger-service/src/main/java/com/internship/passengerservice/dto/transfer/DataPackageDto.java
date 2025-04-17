package com.internship.passengerservice.dto.transfer;


import com.internship.passengerservice.dto.response.ResponseProfileDto;
import lombok.Builder;

import java.util.List;

@Builder
public record DataPackageDto(
        List<ResponseProfileDto> profiles,
        long totalElements,
        int pageNumber,
        int pageSize,
        int totalPages
) {
}
