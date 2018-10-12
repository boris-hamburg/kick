package de.bowo.kick.service;

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

    @PersistenceContext
    private EntityManager entityManager;

    @Override public Spieltag ermittleAktuellenSpieltag() {
        Spieltag spieltag = spieltagRepository.findFirstByOrderById();
        if (spieltag == null) {
            spieltag = new Spieltag();
        }
        return spieltag;
    }

    @Override public void teilnehmer(String firstName, String lastName) {
        User user = userRepository.findByFirstNameAndAndLastName(firstName, lastName);
        if (user == null) {
            user = userRepository.saveAndFlush(new User(firstName, lastName));
            entityManager.detach(user);
        }

        Spieltag spieltag = spieltagRepository.findFirstByOrderById();
        if (spieltag == null) {
            spieltag = spieltagRepository.saveAndFlush(new Spieltag());
            entityManager.detach(spieltag);
        }

        SpieltagUser spieltagUser = new SpieltagUser(spieltag, user);
        spieltagUserRepository.saveAndFlush(spieltagUser);
        entityManager.detach(spieltagUser);
    }

    @Override
    @Transactional
    public void teilnehmer(User user) {
        teilnehmer(user.getFirstName(), user.getLastName());
    }
}
