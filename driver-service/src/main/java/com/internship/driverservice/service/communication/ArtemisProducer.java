package com.internship.driverservice.service.communication;

import com.internship.commonevents.event.ChangeRideStatusEvent;
import com.internship.commonevents.event.ConfirmedPaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArtemisProducer {
    private final JmsTemplate jmsTemplate;

    public void sendRideStatusUpdate(ChangeRideStatusEvent changeRideStatusEvent) {
        sendMessage("ride-status-updates", changeRideStatusEvent, "Ride status update sent");
    }

    public void sendCashPaymentConfirmation(ConfirmedPaymentRequest event) {
        sendMessage("payment-confirmation", event, "Payment confirmation sent");
    }

    private void sendMessage(String queueName, Object message, String logMessage) {
        try {
            jmsTemplate.convertAndSend(queueName, message);
            log.info(logMessage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send message to queue: " + queueName, e);
        }
    }
}
