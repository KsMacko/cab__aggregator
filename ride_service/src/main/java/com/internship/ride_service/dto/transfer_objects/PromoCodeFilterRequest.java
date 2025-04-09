package com.internship.ride_service.dto.transfer_objects;

import com.internship.ride_service.enums.PromoCodeFieldsToFilter;

import java.time.LocalDate;

public record PromoCodeFilterRequest(
        LocalDate createdDate,
        LocalDate validDate,
        int page,
        int size,
        PromoCodeFieldsToFilter sortBy,
        String order
) { }
