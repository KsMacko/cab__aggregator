package com.internship.driverservice.repo;

import com.internship.driverservice.entity.DriverProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DriverProfileRepo extends JpaRepository<DriverProfile, Long>, JpaSpecificationExecutor<DriverProfile> {
    Boolean existsByPhone(String phone);
}