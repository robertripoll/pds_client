package org.udg.pds.cheapyandroid.entity;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "nom",
        "cognoms",
        "telefon"
})
public class UserLoggedUpdate {

    @JsonProperty("nom")
    private String nom;
    @JsonProperty("cognom")
    private String cognom;
    @JsonProperty("telefon")
    private String telefon;

    public UserLoggedUpdate(String nom, String cognoms, String telefon){
        this.nom = nom;
        this.cognom = cognoms;
        this.telefon = telefon;
    }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    public UserLoggedUpdate withNom(String nom) {
        this.nom = nom;
        return this;
    }

    @JsonProperty("cognoms")
    public String getCognoms() {
        return cognom;
    }

    @JsonProperty("cognom")
    public void setCognoms(String cognoms) {
        this.cognom = cognoms;
    }

    public UserLoggedUpdate withCognoms(String cognoms) {
        this.cognom = cognoms;
        return this;
    }

    @JsonProperty("telefon")
    public String getTelefon() {
        return telefon;
    }

    @JsonProperty("telefon")
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public UserLoggedUpdate withTelefon(String telefon) {
        this.telefon = telefon;
        return this;
    }
}