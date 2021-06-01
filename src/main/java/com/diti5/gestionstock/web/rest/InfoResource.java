package com.diti5.gestionstock.web.rest;

import com.diti5.gestionstock.domain.Info;
import com.diti5.gestionstock.service.InfoService;
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
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.diti5.gestionstock.domain.Info}.
 */
@RestController
@RequestMapping("/api")
public class InfoResource {

    private final Logger log = LoggerFactory.getLogger(InfoResource.class);

    private static final String ENTITY_NAME = "info";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InfoService infoService;

    public InfoResource(InfoService infoService) {
        this.infoService = infoService;
    }

    /**
     * {@code POST  /infos} : Create a new info.
     *
     * @param info the info to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new info, or with status {@code 400 (Bad Request)} if the info has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/infos")
    public ResponseEntity<Info> createInfo(@RequestBody Info info) throws URISyntaxException {
        log.debug("REST request to save Info : {}", info);
        if (info.getId() != null) {
            throw new BadRequestAlertException("A new info cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Info result = infoService.save(info);
        return ResponseEntity.created(new URI("/api/infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /infos} : Updates an existing info.
     *
     * @param info the info to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated info,
     * or with status {@code 400 (Bad Request)} if the info is not valid,
     * or with status {@code 500 (Internal Server Error)} if the info couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/infos")
    public ResponseEntity<Info> updateInfo(@RequestBody Info info) throws URISyntaxException {
        log.debug("REST request to update Info : {}", info);
        if (info.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Info result = infoService.save(info);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, info.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /infos} : get all the infos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of infos in body.
     */
    @GetMapping("/infos")
    public ResponseEntity<List<Info>> getAllInfos(Pageable pageable) {
        log.debug("REST request to get a page of Infos");
        Page<Info> page = infoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /infos/:id} : get the "id" info.
     *
     * @param id the id of the info to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the info, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/infos/{id}")
    public ResponseEntity<Info> getInfo(@PathVariable Long id) {
        log.debug("REST request to get Info : {}", id);
        Optional<Info> info = infoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(info);
    }

    /**
     * {@code DELETE  /infos/:id} : delete the "id" info.
     *
     * @param id the id of the info to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/infos/{id}")
    public ResponseEntity<Void> deleteInfo(@PathVariable Long id) {
        log.debug("REST request to delete Info : {}", id);
        infoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
