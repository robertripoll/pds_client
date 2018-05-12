
package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "nom",
        "preu",
        "descripcio",
        "dataPublicacio",
        "preuNegociable",
        "intercanviAcceptat",
        "numVisites",
        "reservat",
        "venedor",
        "transaccio",
        "categoria"
})
public class Producte {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("preu")
    private Double preu;
    @JsonProperty("descripcio")
    private String descripcio;
    @JsonProperty("dataPublicacio")
    private String dataPublicacio;
    @JsonProperty("preuNegociable")
    private Boolean preuNegociable;
    @JsonProperty("intercanviAcceptat")
    private Boolean intercanviAcceptat;
    @JsonProperty("numVisites")
    private Integer numVisites;
    @JsonProperty("reservat")
    private Boolean reservat;
    @JsonProperty("venedor")
    private Venedor venedor;
    @JsonProperty("transaccio")
    private Transaccio transaccio;
    @JsonProperty("categoria")
    private Categoria categoria;
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

    @JsonProperty("preu")
    public Double getPreu() {
        return preu;
    }

    @JsonProperty("preu")
    public void setPreu(Double preu) {
        this.preu = preu;
    }

    @JsonProperty("descripcio")
    public String getDescripcio() {
        return descripcio;
    }

    @JsonProperty("descripcio")
    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    @JsonProperty("dataPublicacio")
    public String getDataPublicacio() {
        return dataPublicacio;
    }

    @JsonProperty("dataPublicacio")
    public void setDataPublicacio(String dataPublicacio) {
        this.dataPublicacio = dataPublicacio;
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

    @JsonProperty("numVisites")
    public Integer getNumVisites() {
        return numVisites;
    }

    @JsonProperty("numVisites")
    public void setNumVisites(Integer numVisites) {
        this.numVisites = numVisites;
    }

    @JsonProperty("reservat")
    public Boolean getReservat() {
        return reservat;
    }

    @JsonProperty("reservat")
    public void setReservat(Boolean reservat) {
        this.reservat = reservat;
    }

    @JsonProperty("venedor")
    public Venedor getVenedor() {
        return venedor;
    }

    @JsonProperty("venedor")
    public void setVenedor(Venedor venedor) {
        this.venedor = venedor;
    }

    @JsonProperty("transaccio")
    public Transaccio getTransaccio() {
        return transaccio;
    }

    @JsonProperty("transaccio")
    public void setTransaccio(Transaccio transaccio) {
        this.transaccio = transaccio;
    }

    @JsonProperty("categoria")
    public Categoria getCategoria() {
        return categoria;
    }

    @JsonProperty("categoria")
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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