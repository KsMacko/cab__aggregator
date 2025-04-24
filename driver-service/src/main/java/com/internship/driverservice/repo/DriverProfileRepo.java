package com.internship.driverservice.repo;

import com.internship.driverservice.entity.DriverProfile;
import com.internship.driverservice.enums.DriverStatus;
import com.internship.driverservice.enums.FareType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DriverProfileRepo extends JpaRepository<DriverProfile, Long>, JpaSpecificationExecutor<DriverProfile> {
    Boolean existsByPhone(String phone);
    List<DriverProfile> findByDriverStatusAndFareType(DriverStatus driverStatus, FareType fareType);
}