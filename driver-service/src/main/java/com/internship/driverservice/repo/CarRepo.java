package com.internship.driverservice.repo;

import com.internship.driverservice.entity.Car;
import com.internship.driverservice.entity.DriverProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CarRepo extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {
    Car findByDriverProfileAndIsCurrent(DriverProfile driverProfile, Boolean current);

    List<Car> findAllByDriverProfile_ProfileId(Long profileId);

    boolean existsByCarNumberIgnoreCase(String carNumber);

}
