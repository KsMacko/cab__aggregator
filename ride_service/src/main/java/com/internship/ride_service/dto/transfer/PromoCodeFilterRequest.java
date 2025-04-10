package com.internship.ride_service.dto.transfer;

import com.internship.ride_service.enums.OrderDirection;
import com.internship.ride_service.enums.PromoCodeFieldsToFilter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record PromoCodeFilterRequest(
        OffsetDateTime createdDate,
        OffsetDateTime validDate,
        int page,
        int size,
        PromoCodeFieldsToFilter sortBy,
        OrderDirection order
) { }
