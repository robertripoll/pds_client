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
        "producte",
        "nombreMissatges",
        "ultimMissatge",
        "missatgesPerLlegir"
})
public class ConversacioChat {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("usuari")
    private Usuari usuari;
    @JsonProperty("producte")
    private Producte producte;
    @JsonProperty("nombreMissatges")
    private Integer nombreMissatges;
    @JsonProperty("ultimMissatge")
    private Object ultimMissatge;
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

    @JsonProperty("usuari")
    public Usuari getUsuari() {
        return usuari;
    }

    @JsonProperty("usuari")
    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
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
    public Object getUltimMissatge() {
        return ultimMissatge;
    }

    @JsonProperty("ultimMissatge")
    public void setUltimMissatge(Object ultimMissatge) {
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}