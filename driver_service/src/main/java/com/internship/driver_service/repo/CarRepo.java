package com.internship.driver_service.repo;

import com.internship.driver_service.entity.Car;
import com.internship.driver_service.entity.DriverProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car, Long> {
    Car findByDriverProfileAndCurrent(DriverProfile driverProfile, Boolean current);

}
