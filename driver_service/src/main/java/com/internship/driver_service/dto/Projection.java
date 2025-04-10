package com.internship.driver_service.dto;

import com.internship.driver_service.enums.DriverStatus;
import com.internship.driver_service.enums.FareType;

public interface Projection {
    Long getProfileId();

    String getFirstName();

    String getLastName();

    FareType getFareType();

    DriverStatus getDriverStatus();

    String getPhone();

    Byte getRate();
}
