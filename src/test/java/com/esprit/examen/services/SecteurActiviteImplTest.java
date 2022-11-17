package com.esprit.examen.services;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Mockito.when(secteurActiviteRepository.findAll()).thenReturn(listSecteurActive);
        List<SecteurActivite> lSA=secteurActiviteService.retrieveAllSecteurActivite();
        Assertions.assertNotNull(lSA);

    }
    @Test
    void testAddSecteurActivite(){
        Mockito.when(secteurActiviteRepository.save(secteurActivite)).thenReturn(secteurActivite);
        SecteurActivite SA = secteurActiviteService.addSecteurActivite(secteurActivite);
        Assertions.assertEquals(secteurActivite, SA);
    }
    @Test
    void testDeletSecteurActivite(){
        secteurActiviteService.deleteSecteurActivite(secteurActivite.getIdSecteurActivite());
        Mockito.verify(secteurActiviteRepository, Mockito.times(1)).deleteById(secteurActivite.getIdSecteurActivite());
    }
    @Test
    void testRetriveOneSecteurActivite(){
        Mockito.when(secteurActiviteRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(secteurActivite));
        @SuppressWarnings("removal")
        SecteurActivite SA = secteurActiviteService.retrieveSecteurActivite(new Long(2));
        Assertions.assertNotNull(SA);
    }
    @Test
    void testUpdateSecteurActivite(){
        secteurActivite.setCodeSecteurActivite("8");
        Mockito.when(secteurActiviteRepository.save(secteurActivite)).thenReturn(secteurActivite);
        SecteurActivite SA = secteurActiviteService.updateSecteurActivite(secteurActivite);
        Assertions.assertEquals(secteurActivite, SA);
    }
}
