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
    private Integer id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("sexe")
    private String sexe;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Emisor(int i, String pep, String home) {
        this.id = 2;
        this.nom = pep;
        this.sexe = home;
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

    @JsonProperty("sexe")
    public String getSexe() {
        return sexe;
    }

    @JsonProperty("sexe")
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}