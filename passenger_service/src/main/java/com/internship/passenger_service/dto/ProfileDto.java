package com.internship.passenger_service.dto;

public record ProfileDto(
        Long profileId,
        String firstName,
        String email,
        String phone,
        Byte rate
){}
