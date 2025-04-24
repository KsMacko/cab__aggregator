package com.internship.passengerservice.service.communication;

import com.internship.commonevents.event.RideParticipantsConfirmation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ride-service", url = "http://localhost:8083/api/v1/rides")
public interface RideFeignClient {
    @GetMapping("/{id}/participants")
    RideParticipantsConfirmation checkParticipants(@PathVariable String id);
}
