package com.esprit.examen.services;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SecteurServiceImplTest {


    @Mock
    SecteurActiviteRepository sr;
    @InjectMocks
    SecteurActiviteServiceImpl ss;


    SecteurActivite s = SecteurActivite.builder().codeSecteurActivite("111ee").libelleSecteurActivite("secteur test").build();
    List<SecteurActivite> listSecteur = new ArrayList<SecteurActivite>() {

        private static final long serialVersionUID = 1L;

        {
            add(SecteurActivite.builder().codeSecteurActivite("22b").libelleSecteurActivite("secteur test").build());
            add(SecteurActivite.builder().codeSecteurActivite("333c").libelleSecteurActivite("secteur test").build());

        }
    };


    @Test
    public void testAddSecteur() {
        Mockito.when(sr.save(s)).thenReturn(s);
        SecteurActivite secteuradd = ss.addSecteurActivite(s);
        Assertions.assertNotNull(secteuradd);
    }

    @Test
    public void testUpdateSecteur() {
        s.setLibelleSecteurActivite("secteur aicha");
        Mockito.when(sr.save(s)).thenReturn(s);
        SecteurActivite sa = ss.updateSecteurActivite(s);
        Assertions.assertEquals(s, sa);
    }

    @Test
    public void RetrieveAllTest() {
        List<SecteurActivite> s = new ArrayList<>();
        s.add(new SecteurActivite());

        when(sr.findAll()).thenReturn(s);

        List<SecteurActivite> expected = ss.retrieveAllSecteurActivite();

        //log.info("get ==> " + String.valueOf(s));

        assertEquals(expected, s);
        verify(sr).findAll();
    }


    @Test
    public void DeleteSecteurTest() throws Exception {
        SecteurActivite s = new SecteurActivite();
        s.setCodeSecteurActivite("22buu");
        s.setLibelleSecteurActivite("del aicha");

        Mockito.when(sr.findById(s.getIdSecteurActivite())).thenReturn(Optional.of(s));
        ss.deleteSecteurActivite(s.getIdSecteurActivite());
        verify(sr).deleteById(s.getIdSecteurActivite());

    }


    @Test()
    public void should_throw_exception_when_user_doesnt_exist() {
        SecteurActivite s = new SecteurActivite();
        s.setCodeSecteurActivite("111111");
        s.setLibelleSecteurActivite("secteur nouv");

        given(sr.findById(anyLong())).willReturn(Optional.ofNullable(null));
        ss.deleteSecteurActivite(s.getIdSecteurActivite());


    }


}