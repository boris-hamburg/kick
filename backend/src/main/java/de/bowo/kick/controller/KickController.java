package de.bowo.kick.controller;

import de.bowo.kick.model.NonParticipant;
import de.bowo.kick.model.Participant;
import de.bowo.kick.repository.NonParticipantRepository;
import de.bowo.kick.repository.ParticipantRepository;
import de.bowo.kick.repository.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class KickController {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private NonParticipantRepository nonParticipantRepository;

    @MessageMapping("/participant")
    @SendTo("/topic/participants")
    public List<Participant> participant(final Participant participant) {
        participantRepository.save(participant);
        final List<Participant> allParticipants = participantRepository.findAll();
        return allParticipants;
    }

    @MessageMapping("/nonparticipant")
    @SendTo("/topic/nonparticipants")
    public List<NonParticipant> nonparticipant(final NonParticipant nonParticipant) {
        nonParticipantRepository.save(nonParticipant);
        final List<NonParticipant> allNonParticipants = nonParticipantRepository.findAll();
        return allNonParticipants;
    }
}
