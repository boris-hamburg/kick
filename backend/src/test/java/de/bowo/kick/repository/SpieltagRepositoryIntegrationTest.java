package de.bowo.kick.repository;

import de.bowo.kick.model.Spieltag;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SpieltagRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SpieltagRepository spieltagRepository;

    @Test
    public void sth() {
        final Spieltag spieltag = new Spieltag();
        entityManager.persist(spieltag);
        entityManager.flush();

        final Spieltag found = spieltagRepository.findFirstByOrderById();

        Assert.assertThat(found.getId(), Matchers.equalTo(spieltag.getId()));
    }

}
