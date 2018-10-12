package de.bowo.kick.repository;

import de.bowo.kick.model.Spieltag;
import de.bowo.kick.model.SpieltagUser;
import de.bowo.kick.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SpieltagUserRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserBaseRepository userRepository;

    @Autowired
    private SpieltagUserRepository spieltagUserRepository;

    @Autowired
    private SpieltagRepository spieltagRepository;

    @Test
    public void sth() {
        User foo = new User("Foo", "Foo");
        User bar = new User("Bar", "Bar");

        foo = userRepository.saveAndFlush(foo);
        entityManager.detach(foo);

        bar = userRepository.saveAndFlush(bar);
        entityManager.detach(bar);

        Spieltag spieltag = new Spieltag();
        spieltag = spieltagRepository.saveAndFlush(spieltag);
        entityManager.detach(spieltag);

        // Tags are attached to the post by creating manually the PostTag join table entries
        // This is allowing us to add extra columns, like the "createdDate"
        SpieltagUser spieltagUserFoo = new SpieltagUser(spieltag, foo);
        spieltagUserRepository.saveAndFlush(spieltagUserFoo);

        SpieltagUser spieltagUserBar = new SpieltagUser(spieltag, bar);
        spieltagUserRepository.saveAndFlush(spieltagUserBar);

        // All the entities have been detached to be sure the following queries will perform fresh sql queries, and won't use the session
        Spieltag dbSpieltag = spieltagRepository.findAll().get(0);
        List<SpieltagUser> spieltagUsers = spieltagUserRepository.findAll();

        Assert.assertEquals(2, spieltagUsers.size());
        // Extra column
        Assert.assertNotNull(spieltagUsers.get(0).joinedDate);
        Assert.assertNotNull(spieltagUsers.get(1).joinedDate);
        Assert.assertEquals(2, dbSpieltag.teilnehmer.size());
        Assert.assertEquals("Foo", dbSpieltag.teilnehmer.get(0).getFirstName());
        Assert.assertEquals("Bar", dbSpieltag.teilnehmer.get(1).getFirstName());
    }

}
