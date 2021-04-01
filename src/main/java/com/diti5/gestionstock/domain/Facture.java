package com.diti5.gestionstock.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;

import com.diti5.gestionstock.domain.enumeration.etat;

/**
 * A Facture.
 */
@Entity
@Table(name = "facture")
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private ZonedDateTime date;

    @Column(name = "quantite")
    private Integer quantite;

    @Column(name = "prenom_client")
    private String prenom_client;

    @Column(name = "nom_client")
    private String nom_client;
    @Column(name = "numero")
    private String numero;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "etat")
    private etat etat;

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

    public Facture date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Facture quantite(Integer quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    public Facture prenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
        return this;
    }

    public void setPrenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
    }

    public String getNom_client() {
        return nom_client;
    }

    public Facture nom_client(String nom_client) {
        this.nom_client = nom_client;
        return this;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public etat getEtat() {
        return etat;
    }

    public Facture etat(etat etat) {
        this.etat = etat;
        return this;
    }

    public void setEtat(etat etat) {
        this.etat = etat;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Facture)) {
            return false;
        }
        return id != null && id.equals(((Facture) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Facture{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", quantite=" + getQuantite() +
            ", prenom_client='" + getPrenom_client() + "'" +
            ", nom_client='" + getNom_client() + "'" +
            ", etat='" + getEtat() + "'" +
            "}";
    }
}
