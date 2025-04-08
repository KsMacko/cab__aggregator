package com.internship.passenger_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import static com.internship.passenger_service.entity.PassengerProfile.Fields.*;

@Getter
@AllArgsConstructor
public enum FieldsToSort {
        FIRST_NAME(firstName),
        EMAIL(email),
        PHONE(phone);

        private final String fieldName;
}
