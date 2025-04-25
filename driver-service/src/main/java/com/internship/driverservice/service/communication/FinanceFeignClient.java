package com.internship.driverservice.service.communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "finance-service")
public interface FinanceFeignClient {
    @PostMapping("/wallet/driver/{id}")
    ResponseEntity<Void> createWallet(@PathVariable Long id);
    @DeleteMapping("/wallet/driver/{id}")
    ResponseEntity<Void> deleteWallet(@PathVariable Long id);
}
