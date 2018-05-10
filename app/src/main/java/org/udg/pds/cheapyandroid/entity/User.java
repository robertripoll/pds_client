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
        "id",
        "correu",
        "nom",
        "cognoms",
        "sexe",
        "telefon",
        "dataNaix",
        "ubicacio"
})
public class User {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("correu")
    private String correu;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("cognoms")
    private String cognoms;
    @JsonProperty("sexe")
    private String sexe;
    @JsonProperty("telefon")
    private String telefon;
    @JsonProperty("dataNaix")
    private String dataNaix;
    @JsonProperty("ubicacio")
    private Ubicacio ubicacio;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public User withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("correu")
    public String getCorreu() {
        return correu;
    }

    @JsonProperty("correu")
    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public User withCorreu(String correu) {
        this.correu = correu;
        return this;
    }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    public User withNom(String nom) {
        this.nom = nom;
        return this;
    }

    @JsonProperty("cognoms")
    public String getCognoms() {
        return cognoms;
    }

    @JsonProperty("cognoms")
    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public User withCognoms(String cognoms) {
        this.cognoms = cognoms;
        return this;
    }

    @JsonProperty("sexe")
    public String getSexe() {
        return sexe;
    }

    @JsonProperty("sexe")
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public User withSexe(String sexe) {
        this.sexe = sexe;
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

    public User withTelefon(String telefon) {
        this.telefon = telefon;
        return this;
    }

    @JsonProperty("dataNaix")
    public String getDataNaix() {
        return dataNaix;
    }

    @JsonProperty("dataNaix")
    public void setDataNaix(String dataNaix) {
        this.dataNaix = dataNaix;
    }

    public User withDataNaix(String dataNaix) {
        this.dataNaix = dataNaix;
        return this;
    }

    @JsonProperty("ubicacio")
    public Ubicacio getUbicacio() {
        return ubicacio;
    }

    @JsonProperty("ubicacio")
    public void setUbicacio(Ubicacio ubicacio) {
        this.ubicacio = ubicacio;
    }

    public User withUbicacio(Ubicacio ubicacio) {
        this.ubicacio = ubicacio;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public User withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}