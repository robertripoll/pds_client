package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pais",
        "ciutat",
        "coordLat",
        "coordLng"
})
public class Ubicacio {

    @JsonProperty("pais")
    private String pais;
    @JsonProperty("ciutat")
    private String ciutat;
    @JsonProperty("coordLat")
    private Double coordLat;
    @JsonProperty("coordLng")
    private Double coordLng;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("pais")
    public String getPais() {
        return pais;
    }

    @JsonProperty("pais")
    public void setPais(String pais) {
        this.pais = pais;
    }

    @JsonProperty("ciutat")
    public String getCiutat() {
        return ciutat;
    }

    @JsonProperty("ciutat")
    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    @JsonProperty("coordLat")
    public Double getCoordLat() {
        return coordLat;
    }

    @JsonProperty("coordLat")
    public void setCoordLat(Double coordLat) {
        this.coordLat = coordLat;
    }

    @JsonProperty("coordLng")
    public Double getCoordLng() {
        return coordLng;
    }

    @JsonProperty("coordLng")
    public void setCoordLng(Double coordLng) {
        this.coordLng = coordLng;
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
