package com.esprit.examen.services;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SecteurActiviteImplTest {

    @Mock
    SecteurActiviteRepository secteurActiviteRepository;

    @InjectMocks
    SecteurActiviteServiceImpl secteurActiviteService;

    SecteurActivite secteurActivite = new SecteurActivite("Test code secteur", "testlibelleSecteur");

    List<SecteurActivite> listSecteurActive = new ArrayList<SecteurActivite>() {
        {
            add(SecteurActivite.builder().codeSecteurActivite("Test code secteur2").libelleSecteurActivite("testlibelleSecteur2").build());
            add(SecteurActivite.builder().codeSecteurActivite("Test code secteur3").libelleSecteurActivite("testlibelleSecteur3").build());
        }
    };

    @Test
    void testRetriveAllSecteurActivite() {
        secteurActiviteRepository.findAll();
    }
}
