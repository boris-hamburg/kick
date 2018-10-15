package de.bowo.kick.service;

import de.bowo.kick.dto.SpieltagDto;
import de.bowo.kick.model.Spieltag;
import de.bowo.kick.repository.SpieltagRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SpieltagServiceImplIntegrationTest {

    @TestConfiguration
    static class SpieltagServiceImplTestContextConfiguration {

        @Bean
        public SpieltagService spieltagService() {
            return new SpieltagServiceImpl();
        }
    }

    @Autowired
    private SpieltagService spieltagService;

    @MockBean
    private SpieltagRepository spieltagRepository;

    @Mock
    private Spieltag mockSpieltag;

    @Test
    public void whenKeinAktuellerSpieltagGefunden_dannWirdNeuerGeliefert() {
        final SpieltagDto spieltag = spieltagService.ermittleAktuellenSpieltag();
        Assert.assertNotNull(spieltag);
    }

    @Test
    public void whenAktuellerSpieltagGefunden_dannHatSpieltagEineId() {
        Mockito.when(mockSpieltag.getId()).thenReturn(1L);
        Mockito.when(spieltagRepository.findFirstByOrderById()).thenReturn(mockSpieltag);

        final SpieltagDto spieltag = spieltagService.ermittleAktuellenSpieltag();

        Assert.assertNotNull(spieltag);
    }

}
