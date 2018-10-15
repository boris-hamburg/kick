package de.bowo.kick.service;

import de.bowo.kick.dto.SpieltagDto;
import de.bowo.kick.model.Spieltag;
import de.bowo.kick.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
@EnableJpaRepositories(basePackages = "de.bowo.kick.repository")
@EntityScan("de.bowo.kick.model")
@AutoConfigureDataJpa
public class IntegrationTest {

    @Autowired
    private SpieltagService spieltagService;

    @Test
    public void sth() {
        spieltagService.teilnehmer(new User("Foo", "Foo"));
        SpieltagDto spieltag = spieltagService.ermittleAktuellenSpieltag();
        Assert.assertNotNull(spieltag);
        Assert.assertEquals(1, spieltag.getTeilnehmer().size());

        spieltagService.teilnehmer(new User("Bar", "Bar"));
        spieltag = spieltagService.ermittleAktuellenSpieltag();
        Assert.assertNotNull(spieltag);
        Assert.assertEquals(2, spieltag.getTeilnehmer().size());
    }
}
