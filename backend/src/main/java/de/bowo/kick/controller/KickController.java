package de.bowo.kick.controller;

import de.bowo.kick.dto.SpieltagDto;
import de.bowo.kick.model.*;
import de.bowo.kick.repository.*;
import de.bowo.kick.service.SpieltagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class KickController {

    @Autowired
    private SpieltagService spieltagService;

    @MessageMapping("/participant")
    @SendTo("/topic/participants")
    public SpieltagDto participant(final Participant participant) {
        spieltagService.teilnehmer(participant.getFirstName(), participant.getLastName());
        final SpieltagDto spieltag = spieltagService.ermittleAktuellenSpieltag();
        return spieltag;
    }

    @MessageMapping("/nonparticipant")
    @SendTo("/topic/nonparticipants")
    public List<NonParticipant> nonparticipant(final NonParticipant nonParticipant) {
        return Collections.emptyList();
    }
}
