package de.bowo.kick.service;

import de.bowo.kick.dto.SpieltagDto;
import de.bowo.kick.model.User;

public interface SpieltagService {

    SpieltagDto ermittleAktuellenSpieltag();

    void teilnehmer(final User user);

    void teilnehmer(final String firstName, final String lastName);

}
