
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
        "preu",
        "preuNegociable",
        "intercanviAcceptat",
        "categoria"
})
public class ProducteCrear {
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("descripcio")
    private String descripcio;
    @JsonProperty("preu")
    private Double preu;
    @JsonProperty("preuNegociable")
    private Boolean preuNegociable;
    @JsonProperty("intercanviAcceptat")
    private Boolean intercanviAcceptat;
    @JsonProperty("categoria")
    private Integer categoria;

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonProperty("descripcio")
    public String getDescripcio() {
        return descripcio;
    }

    @JsonProperty("descripcio")
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    @JsonProperty("preu")
    public Double getPreu() {
        return preu;
    }

    @JsonProperty("preu")
    public void setPreu(Double preu) {
        this.preu = preu;
    }

    @JsonProperty("preuNegociable")
    public Boolean getPreuNegociable() {
        return preuNegociable;
    }

    @JsonProperty("preuNegociable")
    public void setPreuNegociable(Boolean preuNegociable) {
        this.preuNegociable = preuNegociable;
    }

    @JsonProperty("intercanviAcceptat")
    public Boolean getIntercanviAcceptat() {
        return intercanviAcceptat;
    }

    @JsonProperty("intercanviAcceptat")
    public void setIntercanviAcceptat(Boolean intercanviAcceptat) {
        this.intercanviAcceptat = intercanviAcceptat;
    }

    @JsonProperty("categoria")
    public Integer getCategoria() {
        return categoria;
    }

    @JsonProperty("categoria")
    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

}