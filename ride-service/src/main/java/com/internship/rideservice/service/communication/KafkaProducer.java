package com.internship.rideservice.service.communication;

import com.internship.commonevents.event.CashConfirmationRequest;
import com.internship.commonevents.event.RideNotificationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    Logger logger = Logger.getLogger(KafkaProducer.class.getName());
    private final KafkaTemplate<String, CashConfirmationRequest> cashConfirmationKafkaTemplate;
    private final KafkaTemplate<String, RideNotificationEvent> rideNotificationKafkaTemplate;

    public void sendPaymentByCashConfirmation(CashConfirmationRequest paymentConfirmation) {
        logger.info("Sending payment by cash confirmation: " + paymentConfirmation.toString());
        cashConfirmationKafkaTemplate.send("payment-confirmation", paymentConfirmation);
    }

    public void sendRideCreationNotification(RideNotificationEvent rideNotificationEvent) {
        logger.info("Sending ride creation notification: " + rideNotificationEvent.toString());
        rideNotificationKafkaTemplate.send("ride-creation", rideNotificationEvent);
    }
}
