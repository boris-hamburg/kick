package de.bowo.kick.service;

import de.bowo.kick.model.Spieltag;
import de.bowo.kick.model.User;

public interface SpieltagService {

    Spieltag ermittleAktuellenSpieltag();

    void teilnehmer(final User user);

    void teilnehmer(final String firstName, final String lastName);

}
