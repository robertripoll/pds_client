package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "nom",
        "sexe"
})
public class Emisor {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("sexe")
    private String sexe;

    public Emisor(Long id, String nom, String sexe) {
        this.id = id;
        this.nom = nom;
        this.sexe = sexe;
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonProperty("sexe")
    public String getSexe() {
        return sexe;
    }

    @JsonProperty("sexe")
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }


    @Override
    public String toString() {
        return "Emisor{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", sexe='" + sexe + '\'' +
                '}';
    }
}