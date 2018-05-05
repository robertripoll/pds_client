package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "nom"
})

public class Emisor {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nom")
    private String nom;

    public Emisor(int i, String nom) {
        this.id = i;
        this.nom = nom;
    }

    public Emisor() {

    }


    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
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


}
