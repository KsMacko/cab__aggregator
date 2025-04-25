package com.internship.passengerservice.controller;

import com.internship.passengerservice.controller.doc.RateDoc;
import com.internship.passengerservice.dto.mapper.RateMapper;
import com.internship.passengerservice.dto.request.RequestRateDto;
import com.internship.passengerservice.dto.response.ResponseRateDto;
import com.internship.passengerservice.service.RateService;
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
    private final RateMapper rateMapper;

    @Override
    public ResponseEntity<ResponseRateDto> setRateToPassenger(@RequestBody RequestRateDto rateDto) {
        return ResponseEntity.ok(rateMapper.handleEntity(rateService.setNewRate(rateDto)));
    }

    @Override
    public ResponseEntity<Void> deleteRateFromPassenger(@PathVariable Long driverId,
                                                        @PathVariable Long rateId) {
        rateService.deleteRate(driverId, rateId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ResponseRateDto> setRateToDriver(@RequestBody RequestRateDto rateDto) {
        return ResponseEntity.ok(rateService.setRateToDriver(rateDto));
    }

    @Override
    public ResponseEntity<Void> deleteRateFromDriver(@PathVariable Long passengerId,
                                                     @PathVariable Long rateId) {
        rateService.deleteRateFromDriver(passengerId, rateId);
        return ResponseEntity.noContent().build();
    }
}
