package com.diti5.gestionstock.web.rest;

import com.diti5.gestionstock.GestionStockApp;
import com.diti5.gestionstock.domain.Info;
import com.diti5.gestionstock.repository.InfoRepository;
import com.diti5.gestionstock.service.InfoService;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link InfoResource} REST controller.
 */
@SpringBootTest(classes = GestionStockApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InfoResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_ETABLISSEMENT = "AAAAAAAAAA";
    private static final String UPDATED_ETABLISSEMENT = "BBBBBBBBBB";

    @Autowired
    private InfoRepository infoRepository;

    @Autowired
    private InfoService infoService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInfoMockMvc;

    private Info info;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Info createEntity(EntityManager em) {
        Info info = new Info()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .etablissement(DEFAULT_ETABLISSEMENT);
        return info;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Info createUpdatedEntity(EntityManager em) {
        Info info = new Info()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .etablissement(UPDATED_ETABLISSEMENT);
        return info;
    }

    @BeforeEach
    public void initTest() {
        info = createEntity(em);
    }

    @Test
    @Transactional
    public void createInfo() throws Exception {
        int databaseSizeBeforeCreate = infoRepository.findAll().size();
        // Create the Info
        restInfoMockMvc.perform(post("/api/infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(info)))
            .andExpect(status().isCreated());

        // Validate the Info in the database
        List<Info> infoList = infoRepository.findAll();
        assertThat(infoList).hasSize(databaseSizeBeforeCreate + 1);
        Info testInfo = infoList.get(infoList.size() - 1);
        assertThat(testInfo.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testInfo.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testInfo.getEtablissement()).isEqualTo(DEFAULT_ETABLISSEMENT);
    }

    @Test
    @Transactional
    public void createInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = infoRepository.findAll().size();

        // Create the Info with an existing ID
        info.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInfoMockMvc.perform(post("/api/infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(info)))
            .andExpect(status().isBadRequest());

        // Validate the Info in the database
        List<Info> infoList = infoRepository.findAll();
        assertThat(infoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInfos() throws Exception {
        // Initialize the database
        infoRepository.saveAndFlush(info);

        // Get all the infoList
        restInfoMockMvc.perform(get("/api/infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(info.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].etablissement").value(hasItem(DEFAULT_ETABLISSEMENT)));
    }
    
    @Test
    @Transactional
    public void getInfo() throws Exception {
        // Initialize the database
        infoRepository.saveAndFlush(info);

        // Get the info
        restInfoMockMvc.perform(get("/api/infos/{id}", info.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(info.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.etablissement").value(DEFAULT_ETABLISSEMENT));
    }
    @Test
    @Transactional
    public void getNonExistingInfo() throws Exception {
        // Get the info
        restInfoMockMvc.perform(get("/api/infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInfo() throws Exception {
        // Initialize the database
        infoService.save(info);

        int databaseSizeBeforeUpdate = infoRepository.findAll().size();

        // Update the info
        Info updatedInfo = infoRepository.findById(info.getId()).get();
        // Disconnect from session so that the updates on updatedInfo are not directly saved in db
        em.detach(updatedInfo);
        updatedInfo
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .etablissement(UPDATED_ETABLISSEMENT);

        restInfoMockMvc.perform(put("/api/infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInfo)))
            .andExpect(status().isOk());

        // Validate the Info in the database
        List<Info> infoList = infoRepository.findAll();
        assertThat(infoList).hasSize(databaseSizeBeforeUpdate);
        Info testInfo = infoList.get(infoList.size() - 1);
        assertThat(testInfo.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testInfo.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testInfo.getEtablissement()).isEqualTo(UPDATED_ETABLISSEMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingInfo() throws Exception {
        int databaseSizeBeforeUpdate = infoRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInfoMockMvc.perform(put("/api/infos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(info)))
            .andExpect(status().isBadRequest());

        // Validate the Info in the database
        List<Info> infoList = infoRepository.findAll();
        assertThat(infoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInfo() throws Exception {
        // Initialize the database
        infoService.save(info);

        int databaseSizeBeforeDelete = infoRepository.findAll().size();

        // Delete the info
        restInfoMockMvc.perform(delete("/api/infos/{id}", info.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Info> infoList = infoRepository.findAll();
        assertThat(infoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
