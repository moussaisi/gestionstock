package com.diti5.gestionstock.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.diti5.gestionstock.web.rest.TestUtil;

public class UtilisateurTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Utilisateur.class);
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setId(1L);
        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setId(utilisateur1.getId());
        assertThat(utilisateur1).isEqualTo(utilisateur2);
        utilisateur2.setId(2L);
        assertThat(utilisateur1).isNotEqualTo(utilisateur2);
        utilisateur1.setId(null);
        assertThat(utilisateur1).isNotEqualTo(utilisateur2);
    }
}
