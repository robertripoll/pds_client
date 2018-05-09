package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "nom",
        "cognoms",
        "correu",
        "contrasenya",
        "sexe",
        "telefon",
        "dataNaixement",
        "ubicacio"
})
public class User {

    @JsonProperty("id")
    private long id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("cognoms")
    private String cognoms;
    @JsonProperty("correu")
    private String correu;
    @JsonProperty("contrasenya")
    private String contrasenya;
    @JsonProperty("sexe")
    private String sexe;
    @JsonProperty("telefon")
    private String telefon;
    @JsonProperty("dataNaixement")
    private String dataNaixement;
    @JsonProperty("ubicacio")
    private Ubicacio ubicacio;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public long getId() { return id; }

    @JsonProperty("id")
    public void setId(long id) { this.id = id; }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonProperty("cognoms")
    public String getCognoms() {
        return cognoms;
    }

    @JsonProperty("cognoms")
    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    @JsonProperty("correu")
    public String getCorreu() {
        return correu;
    }

    @JsonProperty("correu")
    public void setCorreu(String correu) {
        this.correu = correu;
    }

    @JsonProperty("contrasenya")
    public String getContrasenya() {
        return contrasenya;
    }

    @JsonProperty("contrasenya")
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @JsonProperty("sexe")
    public String getSexe() {
        return sexe;
    }

    @JsonProperty("sexe")
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @JsonProperty("telefon")
    public String getTelefon() {
        return telefon;
    }

    @JsonProperty("telefon")
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @JsonProperty("dataNaixement")
    public String getDataNaixement() {
        return dataNaixement;
    }

    @JsonProperty("dataNaixement")
    public void setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    @JsonProperty("ubicacio")
    public Ubicacio getUbicacio() {
        return ubicacio;
    }

    @JsonProperty("ubicacio")
    public void setUbicacio(Ubicacio ubicacio) {
        this.ubicacio = ubicacio;
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