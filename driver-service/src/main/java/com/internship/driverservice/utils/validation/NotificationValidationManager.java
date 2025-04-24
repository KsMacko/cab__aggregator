package com.internship.driverservice.utils.validation;

import com.internship.driverservice.entity.Notification;
import com.internship.driverservice.enums.notification.NotificationActivity;
import com.internship.driverservice.enums.notification.NotificationType;
import com.internship.driverservice.repo.NotificationRepo;
import com.internship.driverservice.utils.exceptions.InvalidInputException;
import com.internship.driverservice.utils.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationValidationManager {
    private final NotificationRepo notificationRepo;

    public Notification checkNotificationAccordance(Long notificationId, NotificationType notificationType){
        Notification notification = getNotificationByIdIfExists(notificationId);
        if(notification.getType()!= notificationType){
            throw new InvalidInputException("notification.doNotAccord");
        }
        if (notification.getActivity()== NotificationActivity.NON_ACTIVE){
            throw new InvalidInputException("notification.nonActive");
        }
        return notification;
    }
    public Notification getNotificationByIdIfExists(Long notificationId) {
        return notificationRepo.findById(notificationId)
                .orElseThrow(()-> new ResourceNotFoundException("notification.notFound"));
    }
}
