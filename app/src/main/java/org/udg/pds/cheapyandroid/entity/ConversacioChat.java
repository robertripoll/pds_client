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
        "venedorConversa",
        "compradorConversa",
        "producte",
        "nombreMissatges",
        "ultimMissatge",
        "missatgesPerLlegir"
})
public class ConversacioChat {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("venedorConversa")
    private VenedorConversa venedorConversa;
    @JsonProperty("compradorConversa")
    private CompradorConversa compradorConversa;
    @JsonProperty("producte")
    private Producte producte;
    @JsonProperty("nombreMissatges")
    private Integer nombreMissatges;
    @JsonProperty("ultimMissatge")
    private UltimMissatge ultimMissatge;
    @JsonProperty("missatgesPerLlegir")
    private Boolean missatgesPerLlegir;


    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("venedorConversa")
    public VenedorConversa getVenedorConversa() {
        return venedorConversa;
    }

    @JsonProperty("venedorConversa")
    public void setVenedorConversa(VenedorConversa venedorConversa) {
        this.venedorConversa = venedorConversa;
    }

    @JsonProperty("compradorConversa")
    public CompradorConversa getCompradorConversa() {
        return compradorConversa;
    }

    @JsonProperty("compradorConversa")
    public void setCompradorConversa(CompradorConversa compradorConversa) {
        this.compradorConversa = compradorConversa;
    }

    @JsonProperty("producte")
    public Producte getProducte() {
        return producte;
    }

    @JsonProperty("producte")
    public void setProducte(Producte producte) {
        this.producte = producte;
    }

    @JsonProperty("nombreMissatges")
    public Integer getNombreMissatges() {
        return nombreMissatges;
    }

    @JsonProperty("nombreMissatges")
    public void setNombreMissatges(Integer nombreMissatges) {
        this.nombreMissatges = nombreMissatges;
    }

    @JsonProperty("ultimMissatge")
    public UltimMissatge getUltimMissatge() {
        return ultimMissatge;
    }

    @JsonProperty("ultimMissatge")
    public void setUltimMissatge(UltimMissatge ultimMissatge) {
        this.ultimMissatge = ultimMissatge;
    }

    @JsonProperty("missatgesPerLlegir")
    public Boolean getMissatgesPerLlegir() {
        return missatgesPerLlegir;
    }

    @JsonProperty("missatgesPerLlegir")
    public void setMissatgesPerLlegir(Boolean missatgesPerLlegir) {
        this.missatgesPerLlegir = missatgesPerLlegir;
    }


}