package com.internship.driverservice.service.communication;

import com.internship.driverservice.dto.request.RequestRateDto;
import com.internship.driverservice.dto.response.ResponseRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "passenger-service", url = "http://localhost:8081/api/v1/rates")
public interface PassengerFeignClient {
    @PostMapping("/author/driver")
    ResponseRateDto setRateToPassenger(@RequestBody RequestRateDto requestRateDto);
    @DeleteMapping("/{rateId}/author/driver/{driverId}")
    ResponseEntity<Void> deleteRateFromPassenger(@PathVariable Long driverId,
                                                 @PathVariable Long rateId);
}
