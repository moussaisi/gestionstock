package com.diti5.gestionstock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A Stock.
 */
@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantite")
    private Integer quantite;

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "date_der_modif")
    private ZonedDateTime date_der_modif;

    @ManyToOne
    @JsonIgnoreProperties(value = "stocks", allowSetters = true)
    private Produit produit;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Stock quantite(Integer quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public Stock date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public ZonedDateTime getDate_der_modif() {
        return date_der_modif;
    }

    public Stock date_der_modif(ZonedDateTime date_der_modif) {
        this.date_der_modif = date_der_modif;
        return this;
    }

    public void setDate_der_modif(ZonedDateTime date_der_modif) {
        this.date_der_modif = date_der_modif;
    }

    public Produit getProduit() {
        return produit;
    }

    public Stock produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stock)) {
            return false;
        }
        return id != null && id.equals(((Stock) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Stock{" +
            "id=" + getId() +
            ", quantite=" + getQuantite() +
            ", date='" + getDate() + "'" +
            ", date_der_modif='" + getDate_der_modif() + "'" +
            "}";
    }
}
