package org.udg.pds.cheapyandroid.entity;

import java.io.Serializable;
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
        "ruta"
})
public class Imatge implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("ruta")
    private String ruta;

    public Imatge(){
        this.ruta = "";
    }

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    public Imatge withId(Long id) {
        this.id = id;
        return this;
    }

    @JsonProperty("ruta")
    public String getRuta() {
        return ruta;
    }

    @JsonProperty("ruta")
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Imatge withRuta(String ruta) {
        this.ruta = ruta;
        return this;
    }


}