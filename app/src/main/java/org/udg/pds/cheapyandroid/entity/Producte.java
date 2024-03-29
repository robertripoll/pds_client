
package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
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
public class Producte implements Serializable{

    @JsonProperty("id")
    private Long id;
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


    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
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


    @Override
    public String toString() {

        String dadesProducte = "";
        dadesProducte = dadesProducte + "Id d'usuari = " + id + "\n" + "Nom = " + nom + "\n" + "Preu = " + preu + "\n" + "Descripció = " + descripcio + "\n";
        dadesProducte = dadesProducte + "Data de publicació = " + dataPublicacio + "\n" + "Preu negociable? " + preuNegociable + "\n" + "Intercanvi acceptat? " + intercanviAcceptat + "\n";
        dadesProducte = dadesProducte + "Reservat? " + reservat + "\n" + "Número de visites = " + numVisites + "\n";
        dadesProducte = dadesProducte + "Categoria = " + categoria.getNom() + "\n" + "Nom del venedor = " + venedor.getNom() + "\n";

        return dadesProducte;
    }

}