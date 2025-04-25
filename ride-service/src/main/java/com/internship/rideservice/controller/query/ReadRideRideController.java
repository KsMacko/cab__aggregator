package com.internship.rideservice.controller.query;

import com.internship.rideservice.controller.doc.ReadRideDoc;
import com.internship.rideservice.dto.mapper.RideMapper;
import com.internship.rideservice.dto.response.ResponseRideDto;
import com.internship.rideservice.dto.transfer.RideFilterRequest;
import com.internship.rideservice.dto.transfer.RidePackageDto;
import com.internship.rideservice.service.query.ReadRideService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/rides")
@RequiredArgsConstructor
public class ReadRideRideController implements ReadRideDoc {

    private final ReadRideService readRideService;
    private final RideMapper rideMapper;

    @Override
    public ResponseEntity<RidePackageDto> getAllRides(@ModelAttribute RideFilterRequest filterRequest) {
        return ResponseEntity.ok(readRideService.getAllRides(filterRequest));
    }

    @Override
    public ResponseEntity<ResponseRideDto> getRideById(@PathVariable String id) {
        return ResponseEntity.ok(rideMapper.handleEntity(readRideService.getRideById(id)));
    }

}
