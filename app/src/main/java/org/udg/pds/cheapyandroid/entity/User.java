package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
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
        "ubicacio",
        "imatge"
})
public class User implements Serializable {

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
    @JsonProperty("imatge")
    private Imatge imatge;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public User(String correu_, String contrasenya_, String sexe_, String nom_, String cognoms_, String telefon_, String dataNaixament_, Ubicacio ubicacio_) {
        this.correu = correu_;
        this.contrasenya = contrasenya_;
        this.sexe = sexe_;
        this.nom = nom_;
        this.cognoms = cognoms_;
        this.telefon = telefon_;
        this.dataNaixement = dataNaixament_;
        this.ubicacio = ubicacio_;
        this.imatge = new Imatge();
    }

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

    @JsonProperty("imatge")
    public Imatge getImatge() {
        return imatge;
    }

    @JsonProperty("imatge")
    public void setImatge(Imatge imatge) {
        this.imatge = imatge;
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
