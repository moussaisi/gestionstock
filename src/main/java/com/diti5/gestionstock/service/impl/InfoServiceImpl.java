package com.diti5.gestionstock.service.impl;

import com.diti5.gestionstock.service.InfoService;
import com.diti5.gestionstock.domain.Info;
import com.diti5.gestionstock.repository.InfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Info}.
 */
@Service
@Transactional
public class InfoServiceImpl implements InfoService {

    private final Logger log = LoggerFactory.getLogger(InfoServiceImpl.class);

    private final InfoRepository infoRepository;

    public InfoServiceImpl(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @Override
    public Info save(Info info) {
        log.debug("Request to save Info : {}", info);
        return infoRepository.save(info);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Info> findAll(Pageable pageable) {
        log.debug("Request to get all Infos");
        return infoRepository.findAll(pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Info> findOne(Long id) {
        log.debug("Request to get Info : {}", id);
        return infoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Info : {}", id);
        infoRepository.deleteById(id);
    }
}
