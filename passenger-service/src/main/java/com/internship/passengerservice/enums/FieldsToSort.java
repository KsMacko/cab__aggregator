package com.internship.passengerservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.internship.passengerservice.entity.PassengerProfile.Fields.email;
import static com.internship.passengerservice.entity.PassengerProfile.Fields.firstName;
import static com.internship.passengerservice.entity.PassengerProfile.Fields.phone;

@Getter
@AllArgsConstructor
public enum FieldsToSort {
    FIRST_NAME(firstName),
    EMAIL(email),
    PHONE(phone);

    private final String fieldName;
}
