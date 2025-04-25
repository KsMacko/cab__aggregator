package com.internship.passengerservice.service.communication;

import com.internship.passengerservice.dto.request.RequestRateDto;
import com.internship.passengerservice.dto.response.ResponseRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "driver-service")
public interface DriverFeignClient {
    @PostMapping("/author/passenger")
    ResponseRateDto setRateToDriver(@RequestBody RequestRateDto requestRateDto);
    @DeleteMapping("/{rateId}/author/passenger/{passengerId}")
    ResponseEntity<Void> deleteRateFromDriver(@PathVariable Long passengerId,
                                              @PathVariable Long rateId);
}