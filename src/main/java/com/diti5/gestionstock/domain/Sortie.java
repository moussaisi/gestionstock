package com.diti5.gestionstock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A Sortie.
 */
@Entity
@Table(name = "sortie")
public class Sortie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "quantite")
    private Integer quantite;

    @ManyToOne
    @JsonIgnoreProperties(value = "sorties", allowSetters = true)
    private Produit produit;

    @ManyToOne
    @JsonIgnoreProperties(value = "sorties", allowSetters = true)
    private Facture facture;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public Sortie date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Sortie quantite(Integer quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public Sortie produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Facture getFacture() {
        return facture;
    }

    public Sortie facture(Facture facture) {
        this.facture = facture;
        return this;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sortie)) {
            return false;
        }
        return id != null && id.equals(((Sortie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Sortie{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", quantite=" + getQuantite() +
            "}";
    }
}
