
package org.udg.pds.cheapyandroid.entity;


import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "nom",
        "sexe",
        "ubicacio",
        "imatge",
        "nombreValoracions",
        "mitjanaValoracions",
        "nombreCompres",
        "nombreVendes"
})
public class Venedor implements Serializable{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("sexe")
    private String sexe;
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

    @JsonProperty("nombreValoracions")
    public Integer getNombreValoracions() {
        return nombreValoracions;
    }

    @JsonProperty("nombreValoracions")
    public void setNombreValoracions(Integer nombreValoracions) {
        this.nombreValoracions = nombreValoracions;
    }

    @JsonProperty("mitjanaValoracions")
    public Double getMitjanaValoracions() {
        return mitjanaValoracions;
    }

    @JsonProperty("mitjanaValoracions")
    public void setMitjanaValoracions(Double mitjanaValoracions) {
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