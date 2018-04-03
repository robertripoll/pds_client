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
        "latitud",
        "longitud"
})
public class Coordenades {

    @JsonProperty("latitud")
    private Double latitud;
    @JsonProperty("longitud")
    private Double longitud;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("latitud")
    public Double getLatitud() {
        return latitud;
    }

    @JsonProperty("latitud")
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Coordenades withLatitud(Double latitud) {
        this.latitud = latitud;
        return this;
    }

    @JsonProperty("longitud")
    public Double getLongitud() {
        return longitud;
    }

    @JsonProperty("longitud")
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Coordenades withLongitud(Double longitud) {
        this.longitud = longitud;
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

    public Coordenades withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}