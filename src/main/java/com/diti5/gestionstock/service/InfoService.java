package com.diti5.gestionstock.service;

import com.diti5.gestionstock.domain.Info;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Info}.
 */
public interface InfoService {

    /**
     * Save a info.
     *
     * @param info the entity to save.
     * @return the persisted entity.
     */
    Info save(Info info);

    /**
     * Get all the infos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Info> findAll(Pageable pageable);


    /**
     * Get the "id" info.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Info> findOne(Long id);

    /**
     * Delete the "id" info.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
