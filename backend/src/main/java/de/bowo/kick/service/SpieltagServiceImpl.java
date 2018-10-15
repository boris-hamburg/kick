package de.bowo.kick.service;

import de.bowo.kick.dto.SpieltagDto;
import de.bowo.kick.dto.UserDto;
import de.bowo.kick.model.Spieltag;
import de.bowo.kick.model.SpieltagUser;
import de.bowo.kick.model.User;
import de.bowo.kick.repository.SpieltagRepository;
import de.bowo.kick.repository.SpieltagUserRepository;
import de.bowo.kick.repository.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class SpieltagServiceImpl implements SpieltagService {

    @Autowired
    private SpieltagRepository spieltagRepository;

    @Autowired
    private UserBaseRepository userRepository;

    @Autowired
    private SpieltagUserRepository spieltagUserRepository;

    @Override public SpieltagDto ermittleAktuellenSpieltag() {
        final List<UserDto> teilnehmer = spieltagUserRepository.findeTeilnehmer();
        final SpieltagDto spieltag = new SpieltagDto();
        spieltag.setTeilnehmer(teilnehmer);
        return spieltag;
    }

    @Override public void teilnehmer(String firstName, String lastName) {
        User user = userRepository.findByFirstNameAndLastName(firstName, lastName);
        if (user == null) {
            user = userRepository.save(new User(firstName, lastName));
        }

        Spieltag spieltag = spieltagRepository.findFirstByOrderById();
        if (spieltag == null) {
            spieltag = spieltagRepository.save(new Spieltag());
        }

        SpieltagUser spieltagUser = new SpieltagUser(spieltag, user);
        spieltagUserRepository.save(spieltagUser);
    }

    @Override
    @Transactional
    public void teilnehmer(User user) {
        teilnehmer(user.getFirstName(), user.getLastName());
    }
}
