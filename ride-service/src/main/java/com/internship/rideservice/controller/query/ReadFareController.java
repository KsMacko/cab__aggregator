package com.internship.rideservice.controller.query;

import com.internship.rideservice.controller.doc.ReadFareDoc;
import com.internship.rideservice.dto.response.ResponseFareDto;
import com.internship.rideservice.dto.transfer.FarePackageDto;
import com.internship.rideservice.enums.FareType;
import com.internship.rideservice.service.query.ReadFareService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fares")
@RequiredArgsConstructor
public class ReadFareController implements ReadFareDoc {

    private final ReadFareService readFareService;

    @Override
    public FarePackageDto getAllFares() {
        return readFareService.getAllFares();
    }

    @Override
    public ResponseFareDto getFareByType(@PathVariable FareType type) {
        return readFareService.getFareById(type);
    }
}