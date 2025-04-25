package com.internship.driverservice.controller.query;

import com.internship.driverservice.controller.doc.ProfileReadDoc;
import com.internship.driverservice.dto.mapper.ProfileMapper;
import com.internship.driverservice.dto.response.ResponseProfileDto;
import com.internship.driverservice.dto.transfer.DriverFilterRequest;
import com.internship.driverservice.dto.transfer.ProfilePackageDto;
import com.internship.driverservice.service.query.ReadDriverProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class ReadProfileController implements ProfileReadDoc {

    private final ReadDriverProfileService readDriverProfileService;

    @Override
    public ResponseEntity<ProfilePackageDto> findAllDrivers(DriverFilterRequest filter) {
        return ResponseEntity.ok(readDriverProfileService.readAllDrivers(filter));
    }

    @Override
    public ResponseEntity<ResponseProfileDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(readDriverProfileService.readDriverProfileById(id));
    }
}