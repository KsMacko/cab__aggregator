package com.internship.rideservice.service.communication;

import com.internship.commonevents.event.ChangeRideStatusEvent;
import com.internship.rideservice.service.command.CommandRideService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ArtemisConsumer {
    private final CommandRideService commandRideService;
    Logger logger = Logger.getLogger(ArtemisConsumer.class.getName());

    @JmsListener(destination = "ride-status-updates")
    public void receiveRideStatusUpdate(ChangeRideStatusEvent event) {
        try {
            logger.info("Received change ride status event: " + event.toString());
            commandRideService.changeRideStatus(event);
        } catch (Exception e) {
            throw new RuntimeException("Failed to deserialize message", e);
        }
    }
}
