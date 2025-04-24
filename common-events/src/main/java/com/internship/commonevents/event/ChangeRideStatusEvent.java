package com.internship.commonevents.event;

import lombok.Builder;

@Builder
public record ChangeRideStatusEvent(
        String rideId,
        Long driverId,
        String status
)
{
}
