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
        "coordLat",
        "coordLng",
        "ciutat",
        "pais"
})
public class Ubicacio {

    @JsonProperty("coordLat")
    private Double coordLat;
    @JsonProperty("coordLng")
    private Double coordLng;
    @JsonProperty("ciutat")
    private String ciutat;
    @JsonProperty("pais")
    private String pais;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Ubicacio(String pais_, String ciutat_, Double coordLat_, Double coordLng_) {
        pais = pais_;
        ciutat =ciutat_;
        coordLat = coordLat_;
        coordLng = coordLng_;
    }

    @JsonProperty("coordLat")
    public Double getCoordLat() {
        return coordLat;
    }

    @JsonProperty("coordLat")
    public void setCoordLat(Double coordLat) {
        this.coordLat = coordLat;
    }

    public Ubicacio withCoordLat(Double coordLat) {
        this.coordLat = coordLat;
        return this;
    }

    @JsonProperty("coordLng")
    public Double getCoordLng() {
        return coordLng;
    }

    @JsonProperty("coordLng")
    public void setCoordLng(Double coordLng) {
        this.coordLng = coordLng;
    }

    public Ubicacio withCoordLng(Double coordLng) {
        this.coordLng = coordLng;
        return this;
    }

    @JsonProperty("ciutat")
    public String getCiutat() {
        return ciutat;
    }

    @JsonProperty("ciutat")
    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public Ubicacio withCiutat(String ciutat) {
        this.ciutat = ciutat;
        return this;
    }

    @JsonProperty("pais")
    public String getPais() {
        return pais;
    }

    @JsonProperty("pais")
    public void setPais(String pais) {
        this.pais = pais;
    }

    public Ubicacio withPais(String pais) {
        this.pais = pais;
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

    public Ubicacio withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}