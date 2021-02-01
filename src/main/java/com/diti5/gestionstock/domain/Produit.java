package com.diti5.gestionstock.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Produit.
 */
@Entity
@Table(name = "produit")
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "libelle_produit")
    private String libelle_produit;

    @ManyToOne
    @JsonIgnoreProperties(value = "produits", allowSetters = true)
    private Categorie categorie;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle_produit() {
        return libelle_produit;
    }

    public Produit libelle_produit(String libelle_produit) {
        this.libelle_produit = libelle_produit;
        return this;
    }

    public void setLibelle_produit(String libelle_produit) {
        this.libelle_produit = libelle_produit;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public Produit categorie(Categorie categorie) {
        this.categorie = categorie;
        return this;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return id != null && id.equals(((Produit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", libelle_produit='" + getLibelle_produit() + "'" +
            "}";
    }
}
