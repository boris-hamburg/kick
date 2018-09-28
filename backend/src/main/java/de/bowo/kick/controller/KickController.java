package de.bowo.kick.controller;

import de.bowo.kick.model.Participant;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class KickController {

    @MessageMapping("/participant")
    @SendTo("/topic/participants")
    public Participant participant(final Participant participant) {
        System.out.println("Participant: " + participant);
        return participant;
    }

    @MessageMapping("/nonparticipant")
    @SendTo("/topic/nonparticipants")
    public Participant nonparticipant(final Participant participant) {
        System.out.println("NonParticipant: " + participant);
        return participant;
    }
}
