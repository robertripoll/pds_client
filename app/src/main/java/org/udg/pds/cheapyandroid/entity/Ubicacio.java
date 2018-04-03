package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "coordenades",
        "ciutat",
        "pais"
})

public class Ubicacio {

    @JsonProperty("coordenades")
    private Coordenades coordenades;
    @JsonProperty("ciutat")
    private String ciutat;
    @JsonProperty("pais")
    private String pais;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("coordenades")
    public Coordenades getCoordenades() {
        return coordenades;
    }

    @JsonProperty("coordenades")
    public void setCoordenades(Coordenades coordenades) {
        this.coordenades = coordenades;
    }

    @JsonProperty("ciutat")
    public String getCiutat() {
        return ciutat;
    }

    @JsonProperty("ciutat")
    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    @JsonProperty("pais")
    public String getPais() {
        return pais;
    }

    @JsonProperty("pais")
    public void setPais(String pais) {
        this.pais = pais;
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
