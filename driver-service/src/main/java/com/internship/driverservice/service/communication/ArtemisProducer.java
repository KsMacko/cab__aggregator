package com.internship.driverservice.service.communication;

import com.internship.commonevents.event.ChangeRideStatusEvent;
import com.internship.commonevents.event.ConfirmedPaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ArtemisProducer {
    private final JmsTemplate jmsTemplate;
    Logger logger = Logger.getLogger(ArtemisProducer.class.getName());

    public void sendRideStatusUpdate(ChangeRideStatusEvent changeRideStatusEvent) {
        try {
            jmsTemplate.convertAndSend("ride-status-updates", changeRideStatusEvent);
            logger.info("Ride status update sent");
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize message", e);
        }
    }
    public void sendCashPaymentConfirmation(ConfirmedPaymentRequest event) {
        try {
            jmsTemplate.convertAndSend("payment-confirmation", event);
            logger.info("Ride status update sent");
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize message", e);
        }
    }
}
