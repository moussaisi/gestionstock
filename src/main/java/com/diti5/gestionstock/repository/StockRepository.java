package com.diti5.gestionstock.repository;

import com.diti5.gestionstock.domain.Produit_;
import com.diti5.gestionstock.domain.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Stock entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    //Requête qui filtre les produits qui n'existent pas dans le stock
    @Query(value = "SELECT '*' FROM produit p WHERE p.id NOT IN(select s.produit_id from stock s WHERE s.produit_id IS NOT NULL)",nativeQuery = true)
    public List<Produit> FindProductsNoStock();
}
