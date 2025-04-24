package com.internship.driverservice.repo;

import com.internship.driverservice.entity.Notification;
import com.internship.driverservice.enums.notification.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
    Optional<Notification> findByRideIdAndStatus(String rideId, NotificationStatus status);
    List<Notification> findByRideId(String rideId);
}
