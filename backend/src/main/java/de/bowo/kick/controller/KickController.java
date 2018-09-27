package de.bowo.kick.controller;

import de.bowo.kick.model.Participant;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class KickController {

    @MessageMapping("/participant")
    @SendTo("/topic/participants")
    public Participant participant(final Participant particpant) {
        System.out.println("Participant: " + particpant);
        return particpant;
    }
}
