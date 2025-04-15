package com.internship.driver_service.repo;

import com.internship.driver_service.entity.Car;
import com.internship.driver_service.entity.DriverProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CarRepo extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
    Car findByDriverProfileAndIsCurrent(DriverProfile driverProfile, Boolean current);

    List<Car> findAllByDriverProfile_ProfileId(Long profileId);
    boolean existsByCarNumberIgnoreCase(String carNumber);

}
