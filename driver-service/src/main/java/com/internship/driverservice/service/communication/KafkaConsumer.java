package com.internship.driverservice.service.communication;

import com.internship.commonevents.event.CashConfirmationRequest;
import com.internship.commonevents.event.RideNotificationEvent;
import com.internship.driverservice.service.command.CommandNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    Logger logger = Logger.getLogger(KafkaConsumer.class.getName());
    private final CommandNotificationService commandNotificationService;

    @KafkaListener(topics = "ride-creation", groupId = "driver-event")
    public void notifyRideCreationNotification(RideNotificationEvent event) {
        logger.info("Ride creation notification received "+event.toString());
        commandNotificationService.notifyAboutRideCreation(event);
    }
    @KafkaListener(topics = "payment-confirmation", groupId = "driver-event")
    public void notifyPaymentConfirmationNotification(CashConfirmationRequest event) {
        logger.info("Payment confirmation notification received "+event.toString());
        commandNotificationService.notifyAboutRidePaymentByCashConfirmation(event);
    }
}
