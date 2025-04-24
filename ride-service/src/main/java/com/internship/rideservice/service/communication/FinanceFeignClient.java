package com.internship.rideservice.service.communication;

import com.internship.commonevents.event.ConfirmedPaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "finance-service", url = "http://localhost:8084/api/v1/finance")
public interface FinanceFeignClient {
    @PostMapping("/payment")
    ResponseEntity<Void> createCardPayment(@RequestBody ConfirmedPaymentRequest payment);
}

