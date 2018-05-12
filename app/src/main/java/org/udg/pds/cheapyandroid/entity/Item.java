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
        "usuari",
        "nombreMissatges",
        "ultimMissatge",
        "missatgesPerLlegir"
})
public class Item {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("usuari")
    private Usuari usuari;
    @JsonProperty("nombreMissatges")
    private Integer nombreMissatges;
    @JsonProperty("ultimMissatge")
    private UltimMissatge ultimMissatge;
    @JsonProperty("missatgesPerLlegir")
    private Boolean missatgesPerLlegir;
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

    public Item withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("usuari")
    public Usuari getUsuari() {
        return usuari;
    }

    @JsonProperty("usuari")
    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    public Item withUsuari(Usuari usuari) {
        this.usuari = usuari;
        return this;
    }

    @JsonProperty("nombreMissatges")
    public Integer getNombreMissatges() {
        return nombreMissatges;
    }

    @JsonProperty("nombreMissatges")
    public void setNombreMissatges(Integer nombreMissatges) {
        this.nombreMissatges = nombreMissatges;
    }

    public Item withNombreMissatges(Integer nombreMissatges) {
        this.nombreMissatges = nombreMissatges;
        return this;
    }

    @JsonProperty("ultimMissatge")
    public UltimMissatge getUltimMissatge() {
        return ultimMissatge;
    }

    @JsonProperty("ultimMissatge")
    public void setUltimMissatge(UltimMissatge ultimMissatge) {
        this.ultimMissatge = ultimMissatge;
    }

    public Item withUltimMissatge(UltimMissatge ultimMissatge) {
        this.ultimMissatge = ultimMissatge;
        return this;
    }

    @JsonProperty("missatgesPerLlegir")
    public Boolean getMissatgesPerLlegir() {
        return missatgesPerLlegir;
    }

    @JsonProperty("missatgesPerLlegir")
    public void setMissatgesPerLlegir(Boolean missatgesPerLlegir) {
        this.missatgesPerLlegir = missatgesPerLlegir;
    }

    public Item withMissatgesPerLlegir(Boolean missatgesPerLlegir) {
        this.missatgesPerLlegir = missatgesPerLlegir;
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

    public Item withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}