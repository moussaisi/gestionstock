package com.diti5.gestionstock.repository;

import com.diti5.gestionstock.domain.Sortie;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Sortie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SortieRepository extends JpaRepository<Sortie, Long> {
}
