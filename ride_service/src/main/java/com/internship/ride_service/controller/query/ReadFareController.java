package com.internship.ride_service.controller.query;

import com.internship.ride_service.controller.doc.ReadFareDoc;
import com.internship.ride_service.dto.FareDto;
import com.internship.ride_service.dto.transfer.FarePackageDto;
import com.internship.ride_service.enums.FareType;
import com.internship.ride_service.service.query.ReadFareService;
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
    public FareDto getFareByType(@PathVariable FareType type) {
        return readFareService.getFareById(type);
    }
}