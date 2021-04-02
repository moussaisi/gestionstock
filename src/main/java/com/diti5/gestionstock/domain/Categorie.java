package com.diti5.gestionstock.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Categorie.
 */
@Entity
@Table(name = "categorie")
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "libelle_categorie", nullable = false, unique = true)
    private String libelle_categorie;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle_categorie() {
        return libelle_categorie;
    }

    public Categorie libelle_categorie(String libelle_categorie) {
        this.libelle_categorie = libelle_categorie;
        return this;
    }

    public void setLibelle_categorie(String libelle_categorie) {
        this.libelle_categorie = libelle_categorie;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Categorie)) {
            return false;
        }
        return id != null && id.equals(((Categorie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Categorie{" +
            "id=" + getId() +
            ", libelle_categorie='" + getLibelle_categorie() + "'" +
            "}";
    }
}
