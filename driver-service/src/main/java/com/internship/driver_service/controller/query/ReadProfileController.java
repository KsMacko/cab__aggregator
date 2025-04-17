package com.internship.driver_service.controller.query;

import com.internship.driver_service.controller.doc.ProfileReadDoc;
import com.internship.driver_service.dto.response.ResponseProfileDto;
import com.internship.driver_service.dto.transfer.DriverFilterRequest;
import com.internship.driver_service.dto.transfer.ProfilePackageDto;
import com.internship.driver_service.service.query.ReadDriverProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/drivers")
@RequiredArgsConstructor
public class ReadProfileController implements ProfileReadDoc {

    private final ReadDriverProfileService readDriverProfileService;

    @Override
    public ProfilePackageDto findAllDrivers(DriverFilterRequest filter) {
        return readDriverProfileService.readAllDrivers(filter);
    }

    @Override
    public ResponseProfileDto findById(@PathVariable Long id) {
        return readDriverProfileService.readDriverProfileById(id);
    }
}