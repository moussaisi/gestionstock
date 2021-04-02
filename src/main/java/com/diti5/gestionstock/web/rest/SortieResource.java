package com.diti5.gestionstock.web.rest;

import com.diti5.gestionstock.domain.Sortie;
import com.diti5.gestionstock.repository.SortieRepository;
import com.diti5.gestionstock.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.diti5.gestionstock.domain.Sortie}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SortieResource {

    private final Logger log = LoggerFactory.getLogger(SortieResource.class);

    private static final String ENTITY_NAME = "sortie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SortieRepository sortieRepository;

    public SortieResource(SortieRepository sortieRepository) {
        this.sortieRepository = sortieRepository;
    }

    /**
     * {@code POST  /sorties} : Create a new sortie.
     *
     * @param sortie the sortie to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new sortie, or with status {@code 400 (Bad Request)} if the sortie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sorties")
    public ResponseEntity<Sortie> createSortie(@RequestBody Sortie sortie) throws URISyntaxException {
        log.debug("REST request to save Sortie : {}", sortie);
        if (sortie.getId() != null) {
            throw new BadRequestAlertException("A new sortie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Sortie result = sortieRepository.save(sortie);
        return ResponseEntity.created(new URI("/api/sorties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sorties} : Updates an existing sortie.
     *
     * @param sortie the sortie to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated sortie,
     * or with status {@code 400 (Bad Request)} if the sortie is not valid,
     * or with status {@code 500 (Internal Server Error)} if the sortie couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sorties")
    public ResponseEntity<Sortie> updateSortie(@RequestBody Sortie sortie) throws URISyntaxException {
        log.debug("REST request to update Sortie : {}", sortie);
        if (sortie.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Sortie result = sortieRepository.save(sortie);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, sortie.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /sorties} : get all the sorties.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of sorties in body.
     */
    @GetMapping("/sorties")
    public ResponseEntity<List<Sortie>> getAllSorties(Pageable pageable) {
        log.debug("REST request to get a page of Sorties");
        Page<Sortie> page = sortieRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sorties/:id} : get the "id" sortie.
     *
     * @param id the id of the sortie to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the sortie, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sorties/{id}")
    public ResponseEntity<Sortie> getSortie(@PathVariable Long id) {
        log.debug("REST request to get Sortie : {}", id);
        Optional<Sortie> sortie = sortieRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(sortie);
    }

    /**
     * {@code DELETE  /sorties/:id} : delete the "id" sortie.
     *
     * @param id the id of the sortie to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sorties/{id}")
    public ResponseEntity<Void> deleteSortie(@PathVariable Long id) {
        log.debug("REST request to delete Sortie : {}", id);
        sortieRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
