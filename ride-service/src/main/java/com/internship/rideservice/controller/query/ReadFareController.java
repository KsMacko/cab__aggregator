package com.internship.rideservice.controller.query;

import com.internship.rideservice.controller.doc.ReadFareDoc;
import com.internship.rideservice.dto.mapper.FareMapper;
import com.internship.rideservice.dto.response.ResponseFareDto;
import com.internship.rideservice.dto.transfer.FarePackageDto;
import com.internship.rideservice.enums.FareType;
import com.internship.rideservice.service.query.ReadFareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fares")
@RequiredArgsConstructor
public class ReadFareController implements ReadFareDoc {

    private final ReadFareService readFareService;
    private final FareMapper fareMapper;

    @Override
    public ResponseEntity<FarePackageDto> getAllFares() {
        return ResponseEntity.ok(readFareService.getAllFares());
    }

    @Override
    public ResponseEntity<ResponseFareDto> getFareByType(@PathVariable FareType type) {
        return ResponseEntity.ok(fareMapper.handleEntity(readFareService.getFareById(type)));
    }
}