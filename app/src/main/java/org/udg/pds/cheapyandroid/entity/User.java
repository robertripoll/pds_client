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
        "nom",
        "cognoms",
        "dataNaixement",
        "correu",
        "nombreVendes",
        "nombreCompres",
        "nombreValoracions",
        "mitjanaValoracions",
        "ubicacio",
        "imatgeURL"
})
public class User {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("cognoms")
    private String cognoms;
    @JsonProperty("dataNaixement")
    private String dataNaixement;
    @JsonProperty("correu")
    private String correu;
    @JsonProperty("nombreVendes")
    private Integer nombreVendes;
    @JsonProperty("nombreCompres")
    private Integer nombreCompres;
    @JsonProperty("nombreValoracions")
    private Integer nombreValoracions;
    @JsonProperty("mitjanaValoracions")
    private Double mitjanaValoracions;
    @JsonProperty("ubicacio")
    private Ubicacio ubicacio;
    @JsonProperty("imatgeURL")
    private String imatgeURL;
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

    @JsonProperty("dataNaixement")
    public String getDataNaixement() {
        return dataNaixement;
    }

    @JsonProperty("dataNaixement")
    public void setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public User withDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
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

    @JsonProperty("nombreVendes")
    public Integer getNombreVendes() {
        return nombreVendes;
    }

    @JsonProperty("nombreVendes")
    public void setNombreVendes(Integer nombreVendes) {
        this.nombreVendes = nombreVendes;
    }

    public User withNombreVendes(Integer nombreVendes) {
        this.nombreVendes = nombreVendes;
        return this;
    }

    @JsonProperty("nombreCompres")
    public Integer getNombreCompres() {
        return nombreCompres;
    }

    @JsonProperty("nombreCompres")
    public void setNombreCompres(Integer nombreCompres) {
        this.nombreCompres = nombreCompres;
    }

    public User withNombreCompres(Integer nombreCompres) {
        this.nombreCompres = nombreCompres;
        return this;
    }

    @JsonProperty("nombreValoracions")
    public Integer getNombreValoracions() {
        return nombreValoracions;
    }

    @JsonProperty("nombreValoracions")
    public void setNombreValoracions(Integer nombreValoracions) {
        this.nombreValoracions = nombreValoracions;
    }

    public User withNombreValoracions(Integer nombreValoracions) {
        this.nombreValoracions = nombreValoracions;
        return this;
    }

    @JsonProperty("mitjanaValoracions")
    public Double getMitjanaValoracions() {
        return mitjanaValoracions;
    }

    @JsonProperty("mitjanaValoracions")
    public void setMitjanaValoracions(Double mitjanaValoracions) {
        this.mitjanaValoracions = mitjanaValoracions;
    }

    public User withMitjanaValoracions(Double mitjanaValoracions) {
        this.mitjanaValoracions = mitjanaValoracions;
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

    @JsonProperty("imatgeURL")
    public String getImatgeURL() {
        return imatgeURL;
    }

    @JsonProperty("imatgeURL")
    public void setImatgeURL(String imatgeURL) {
        this.imatgeURL = imatgeURL;
    }

    public User withImatgeURL(String imatgeURL) {
        this.imatgeURL = imatgeURL;
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