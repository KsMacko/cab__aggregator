package com.internship.passenger_service.dto.transfer_objects;

import com.internship.passenger_service.enums.FieldsToSort;
import com.internship.passenger_service.enums.OrderDirection;

public record ProfileFilterRequest (

        String email,
        String phone,
        Byte rate,
        int page,
        int size,
        FieldsToSort sortBy,
        OrderDirection order
){ }
