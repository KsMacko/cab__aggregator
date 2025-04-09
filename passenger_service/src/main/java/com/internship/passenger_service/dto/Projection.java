package com.internship.passenger_service.dto;

public interface Projection {
    Long getProfileId();
    String getFirstName();
    String getEmail();
    String getPhone();
    Byte getRate();
}