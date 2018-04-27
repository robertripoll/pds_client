package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "nombreMissatges",
        "missatgesPerLlegir",
        "ultimMissatge",
        "producte",
        "venedor"
})


public class Conversacio implements Serializable{

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("nombreMissatges")
    private Integer nombreMissatges;

    @JsonProperty("missatgesPerLlegir")
    private Boolean missatgesPerLlegir;

    @JsonProperty("ultimMissatge")
    private String ultimMissatge;

    @JsonProperty("producte")
    private Producte_ producte;

    @JsonProperty("venedor")
    private Venedor venedor;

    public Conversacio(Integer id, Integer nombreMissatges, Boolean missatgesPerLlegir, String ultimMissatge, Producte_ producte, Venedor venedor) {
        this.id = id;
        this.nombreMissatges = nombreMissatges;
        this.missatgesPerLlegir = missatgesPerLlegir;
        this.ultimMissatge = ultimMissatge;
        this.producte = producte;
        this.venedor = venedor;
    }

    public Conversacio(Producte_ producte, Venedor venedor) {
        this.id = null;
        this.nombreMissatges = 0;
        this.missatgesPerLlegir = false;
        this.ultimMissatge = "";
        this.producte = producte;
        this.venedor = venedor;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("nombreMissatges")
    public Integer getnombreMissatges() {
        return nombreMissatges;
    }

    @JsonProperty("nombreMissatges")
    public void setnombreMissatges(Integer nombreMissatges) {
        this.nombreMissatges = nombreMissatges;
    }

    @JsonProperty("missatgesPerLlegir")
    public Boolean getmissatgesPerLlegir() {
        return missatgesPerLlegir;
    }

    @JsonProperty("missatgesPerLlegir")
    public void setmissatgesPerLlegir(Boolean missatgesPerLlegir) {
        this.missatgesPerLlegir = missatgesPerLlegir;
    }

    @JsonProperty("ultimMissatge")
    public String getultimMissatge() {
        return ultimMissatge;
    }

    @JsonProperty("ultimMissatge")
    public void setultimMissatge(String ultimMissatge) {
        this.ultimMissatge = ultimMissatge;
    }

    @JsonProperty("producte")
    public Producte_ getproducte() {
        return producte;
    }

    @JsonProperty("producte")
    public void setproducte(Producte_ producte) {
        this.producte = producte;
    }

    @JsonProperty("venedor")
    public Venedor getvenedor() {
        return venedor;
    }

    @JsonProperty("venedor")
    public void setvenedor(Venedor venedor) {
        this.venedor = venedor;
    }


}
