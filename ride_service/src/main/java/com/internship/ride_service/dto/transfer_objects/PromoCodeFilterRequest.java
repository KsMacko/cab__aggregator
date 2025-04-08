package com.internship.ride_service.dto.transfer_objects;

import java.time.LocalDate;

public record PromoCodeFilterRequest(
        LocalDate createdDate,
        LocalDate validDate,
        int page,
        int size,
        String sortBy,
        String order
) { }
