package com.diti5.gestionstock.web.rest;

import com.diti5.gestionstock.GestionStockApp;
import com.diti5.gestionstock.domain.Sortie;
import com.diti5.gestionstock.repository.SortieRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.diti5.gestionstock.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SortieResource} REST controller.
 */
@SpringBootTest(classes = GestionStockApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class SortieResourceIT {

    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_QUANTITE = 1;
    private static final Integer UPDATED_QUANTITE = 2;

    @Autowired
    private SortieRepository sortieRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSortieMockMvc;

    private Sortie sortie;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sortie createEntity(EntityManager em) {
        Sortie sortie = new Sortie()
            .date(DEFAULT_DATE)
            .quantite(DEFAULT_QUANTITE);
        return sortie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sortie createUpdatedEntity(EntityManager em) {
        Sortie sortie = new Sortie()
            .date(UPDATED_DATE)
            .quantite(UPDATED_QUANTITE);
        return sortie;
    }

    @BeforeEach
    public void initTest() {
        sortie = createEntity(em);
    }

    @Test
    @Transactional
    public void createSortie() throws Exception {
        int databaseSizeBeforeCreate = sortieRepository.findAll().size();
        // Create the Sortie
        restSortieMockMvc.perform(post("/api/sorties")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sortie)))
            .andExpect(status().isCreated());

        // Validate the Sortie in the database
        List<Sortie> sortieList = sortieRepository.findAll();
        assertThat(sortieList).hasSize(databaseSizeBeforeCreate + 1);
        Sortie testSortie = sortieList.get(sortieList.size() - 1);
        assertThat(testSortie.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testSortie.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
    }

    @Test
    @Transactional
    public void createSortieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sortieRepository.findAll().size();

        // Create the Sortie with an existing ID
        sortie.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSortieMockMvc.perform(post("/api/sorties")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sortie)))
            .andExpect(status().isBadRequest());

        // Validate the Sortie in the database
        List<Sortie> sortieList = sortieRepository.findAll();
        assertThat(sortieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSorties() throws Exception {
        // Initialize the database
        sortieRepository.saveAndFlush(sortie);

        // Get all the sortieList
        restSortieMockMvc.perform(get("/api/sorties?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sortie.getId().intValue())))
            .andExpect(jsonPath("$.[*].date").value(hasItem(sameInstant(DEFAULT_DATE))))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE)));
    }
    
    @Test
    @Transactional
    public void getSortie() throws Exception {
        // Initialize the database
        sortieRepository.saveAndFlush(sortie);

        // Get the sortie
        restSortieMockMvc.perform(get("/api/sorties/{id}", sortie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sortie.getId().intValue()))
            .andExpect(jsonPath("$.date").value(sameInstant(DEFAULT_DATE)))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE));
    }
    @Test
    @Transactional
    public void getNonExistingSortie() throws Exception {
        // Get the sortie
        restSortieMockMvc.perform(get("/api/sorties/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSortie() throws Exception {
        // Initialize the database
        sortieRepository.saveAndFlush(sortie);

        int databaseSizeBeforeUpdate = sortieRepository.findAll().size();

        // Update the sortie
        Sortie updatedSortie = sortieRepository.findById(sortie.getId()).get();
        // Disconnect from session so that the updates on updatedSortie are not directly saved in db
        em.detach(updatedSortie);
        updatedSortie
            .date(UPDATED_DATE)
            .quantite(UPDATED_QUANTITE);

        restSortieMockMvc.perform(put("/api/sorties")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSortie)))
            .andExpect(status().isOk());

        // Validate the Sortie in the database
        List<Sortie> sortieList = sortieRepository.findAll();
        assertThat(sortieList).hasSize(databaseSizeBeforeUpdate);
        Sortie testSortie = sortieList.get(sortieList.size() - 1);
        assertThat(testSortie.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testSortie.getQuantite()).isEqualTo(UPDATED_QUANTITE);
    }

    @Test
    @Transactional
    public void updateNonExistingSortie() throws Exception {
        int databaseSizeBeforeUpdate = sortieRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSortieMockMvc.perform(put("/api/sorties")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(sortie)))
            .andExpect(status().isBadRequest());

        // Validate the Sortie in the database
        List<Sortie> sortieList = sortieRepository.findAll();
        assertThat(sortieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSortie() throws Exception {
        // Initialize the database
        sortieRepository.saveAndFlush(sortie);

        int databaseSizeBeforeDelete = sortieRepository.findAll().size();

        // Delete the sortie
        restSortieMockMvc.perform(delete("/api/sorties/{id}", sortie.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Sortie> sortieList = sortieRepository.findAll();
        assertThat(sortieList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
