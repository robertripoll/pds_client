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
        "ubicacio",
        "imatge",
        "nombreValoracions",
        "mitjanaValoracions",
        "nombreCompres",
        "nombreVendes"
})
public class UserLogged {

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
    @JsonProperty("imatge")
    private Imatge imatge;
    @JsonProperty("nombreValoracions")
    private Integer nombreValoracions;
    @JsonProperty("mitjanaValoracions")
    private Double mitjanaValoracions;
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

    public UserLogged withId(Integer id) {
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

    public UserLogged withCorreu(String correu) {
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

    public UserLogged withNom(String nom) {
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

    public UserLogged withCognoms(String cognoms) {
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

    public UserLogged withSexe(String sexe) {
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

    public UserLogged withTelefon(String telefon) {
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

    public UserLogged withDataNaix(String dataNaix) {
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

    public UserLogged withUbicacio(Ubicacio ubicacio) {
        this.ubicacio = ubicacio;
        return this;
    }

    @JsonProperty("imatge")
    public Imatge getImatge() {
        return imatge;
    }

    @JsonProperty("imatge")
    public void setImatge(Imatge imatge) {
        this.imatge = imatge;
    }

    public UserLogged withImatge(Imatge imatge) {
        this.imatge = imatge;
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

    public UserLogged withNombreValoracions(Integer nombreValoracions) {
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

    public UserLogged withMitjanaValoracions(Double mitjanaValoracions) {
        this.mitjanaValoracions = mitjanaValoracions;
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

    public UserLogged withNombreCompres(Integer nombreCompres) {
        this.nombreCompres = nombreCompres;
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

    public UserLogged withNombreVendes(Integer nombreVendes) {
        this.nombreVendes = nombreVendes;
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

    public UserLogged withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}