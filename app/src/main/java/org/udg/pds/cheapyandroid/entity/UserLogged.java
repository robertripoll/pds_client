package org.udg.pds.cheapyandroid.entity;

import java.io.Serializable;
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
        "ubicacio",
        "nombreValoracions",
        "mitjanaValoracions",
        "nombreCompres",
        "nombreVendes"
})
public class UserLogged implements Serializable{

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
    @JsonProperty("nombreValoracions")
    private Integer nombreValoracions;
    @JsonProperty("mitjanaValoracions")
    private Object mitjanaValoracions;
    @JsonProperty("nombreCompres")
    private Integer nombreCompres;
    @JsonProperty("nombreVendes")
    private Integer nombreVendes;
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

    @JsonProperty("correu")
    public String getCorreu() {
        return correu;
    }

    @JsonProperty("correu")
    public void setCorreu(String correu) {
        this.correu = correu;
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

    @JsonProperty("dataNaix")
    public String getDataNaix() {
        return dataNaix;
    }

    @JsonProperty("dataNaix")
    public void setDataNaix(String dataNaix) {
        this.dataNaix = dataNaix;
    }

    @JsonProperty("ubicacio")
    public Ubicacio getUbicacio() {
        return ubicacio;
    }

    @JsonProperty("ubicacio")
    public void setUbicacio(Ubicacio ubicacio) {
        this.ubicacio = ubicacio;
    }

    @JsonProperty("nombreValoracions")
    public Integer getNombreValoracions() {
        return nombreValoracions;
    }

    @JsonProperty("nombreValoracions")
    public void setNombreValoracions(Integer nombreValoracions) {
        this.nombreValoracions = nombreValoracions;
    }

    @JsonProperty("mitjanaValoracions")
    public Object getMitjanaValoracions() {
        return mitjanaValoracions;
    }

    @JsonProperty("mitjanaValoracions")
    public void setMitjanaValoracions(Object mitjanaValoracions) {
        this.mitjanaValoracions = mitjanaValoracions;
    }

    @JsonProperty("nombreCompres")
    public Integer getNombreCompres() {
        return nombreCompres;
    }

    @JsonProperty("nombreCompres")
    public void setNombreCompres(Integer nombreCompres) {
        this.nombreCompres = nombreCompres;
    }

    @JsonProperty("nombreVendes")
    public Integer getNombreVendes() {
        return nombreVendes;
    }

    @JsonProperty("nombreVendes")
    public void setNombreVendes(Integer nombreVendes) {
        this.nombreVendes = nombreVendes;
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