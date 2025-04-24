package com.internship.commonevents.event;

import lombok.Builder;

import java.util.List;

@Builder
public record RideNotificationEvent(
    String rideId,
    String fare,
    String pickupLocation,
    List<String> endLocation
){
}
