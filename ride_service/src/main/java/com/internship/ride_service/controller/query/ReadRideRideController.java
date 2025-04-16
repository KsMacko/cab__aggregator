package com.internship.ride_service.controller.query;

import com.internship.ride_service.controller.doc.ReadRideDoc;
import com.internship.ride_service.dto.RideDto;
import com.internship.ride_service.dto.transfer.RideFilterRequest;
import com.internship.ride_service.dto.transfer.RidePackageDto;
import com.internship.ride_service.service.query.ReadRideService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/rides")
@RequiredArgsConstructor
public class ReadRideRideController implements ReadRideDoc {

    private final ReadRideService readRideService;
    @Override
    public RidePackageDto getAllRides(@ModelAttribute RideFilterRequest filterRequest) {
        return readRideService.getAllRides(filterRequest);
    }

    @Override
    public RideDto getRideById(@PathVariable String id) {
        return readRideService.getRideById(id);
    }

}
