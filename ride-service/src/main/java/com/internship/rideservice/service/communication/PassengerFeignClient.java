package com.internship.rideservice.service.communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "passenger-service", url = "http://localhost:8081/api/v1/passengers")
public interface PassengerFeignClient {
    @GetMapping("/{id}")
    ResponseEntity<Void> getPassengerById(@PathVariable Long id);
}
