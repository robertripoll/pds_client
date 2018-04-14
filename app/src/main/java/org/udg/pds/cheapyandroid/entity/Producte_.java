
package org.udg.pds.cheapyandroid.entity;

import java.util.HashMap;
import java.util.List;
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
    "preu",
    "descripcio",
    "dataPublicacio",
    "preuNegociable",
    "intercanviAcceptat",
    "reservat",
    "numeroVisites",
    "categoria",
    "venedor",
    "imatges"
})
public class Producte_ {

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
    @JsonProperty("reservat")
    private Boolean reservat;
    @JsonProperty("numeroVisites")
    private Integer numeroVisites;
    @JsonProperty("categoria")
    private Categoria categoria;
    @JsonProperty("venedor")
    private Venedor venedor;
    @JsonProperty("imatges")
    private List<Imatge> imatges = null;
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

    @JsonProperty("reservat")
    public Boolean getReservat() {
        return reservat;
    }

    @JsonProperty("reservat")
    public void setReservat(Boolean reservat) {
        this.reservat = reservat;
    }

    @JsonProperty("numeroVisites")
    public Integer getNumeroVisites() {
        return numeroVisites;
    }

    @JsonProperty("numeroVisites")
    public void setNumeroVisites(Integer numeroVisites) {
        this.numeroVisites = numeroVisites;
    }

    @JsonProperty("categoria")
    public Categoria getCategoria() {
        return categoria;
    }

    @JsonProperty("categoria")
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @JsonProperty("venedor")
    public Venedor getVenedor() {
        return venedor;
    }

    @JsonProperty("venedor")
    public void setVenedor(Venedor venedor) {
        this.venedor = venedor;
    }

    @JsonProperty("imatges")
    public List<Imatge> getImatges() {
        return imatges;
    }

    @JsonProperty("imatges")
    public void setImatges(List<Imatge> imatges) {
        this.imatges = imatges;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {

        String dadesProducte = "";
        dadesProducte = dadesProducte + "id = " + id + "\n" + "nom = " + nom + "\n" + "preu = " + preu + "\n" + "descripcio = " + descripcio + "\n";
        dadesProducte = dadesProducte + "dataPublicacio = " + dataPublicacio + "\n" + "preuNegociable = " + preuNegociable + "\n" + "intercanviAcceptat = " + intercanviAcceptat + "\n";
        dadesProducte = dadesProducte + "reservat = " + reservat + "\n" + "numeroVisites = " + numeroVisites + "\n";
        dadesProducte = dadesProducte + "cateogira = " + categoria.getNom() + "\n" + "venedor = " + venedor.getNom() + "\n";

        return dadesProducte;
    }
}
