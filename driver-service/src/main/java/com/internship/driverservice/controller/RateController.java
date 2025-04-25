package com.internship.driverservice.controller;

import com.internship.driverservice.controller.doc.RateDoc;
import com.internship.driverservice.dto.request.RequestRateDto;
import com.internship.driverservice.dto.response.ResponseRateDto;
import com.internship.driverservice.service.command.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rates")
@RequiredArgsConstructor
public class RateController implements RateDoc {
    private final RateService rateService;

    @Override
    public ResponseEntity<ResponseRateDto> setRateToDriver(@RequestBody RequestRateDto rateDto) {
        return ResponseEntity.ok(rateService.setNewRate(rateDto));
    }
    @Override
    public ResponseEntity<Void> deleteRateFromDriver(@PathVariable Long passengerId,
                                                     @PathVariable Long rateId) {
        rateService.deleteRate(passengerId, rateId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ResponseRateDto> setRateToPassenger(@RequestBody RequestRateDto rateDto) {
        return ResponseEntity.ok(rateService.setRateToPassenger(rateDto));
    }

    @Override
    public ResponseEntity<Void> deleteRateFromPassenger(@PathVariable Long driverId,
                                                        @PathVariable Long rateId) {
        rateService.deleteRateFromPassenger(driverId, rateId);
        return ResponseEntity.noContent().build();
    }

}
