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
        "emisor",
        "receptor",
        "estat",
        "missatge",
        "dataEnviament"
})
public class UltimMissatge {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("emisor")
    private Emisor emisor;
    @JsonProperty("receptor")
    private Receptor receptor;
    @JsonProperty("estat")
    private String estat;
    @JsonProperty("missatge")
    private String missatge;
    @JsonProperty("dataEnviament")
    private String dataEnviament;


    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("emisor")
    public Emisor getEmisor() {
        return emisor;
    }

    @JsonProperty("emisor")
    public void setEmisor(Emisor emisor) {
        this.emisor = emisor;
    }

    @JsonProperty("receptor")
    public Receptor getReceptor() {
        return receptor;
    }

    @JsonProperty("receptor")
    public void setReceptor(Receptor receptor) {
        this.receptor = receptor;
    }

    @JsonProperty("estat")
    public String getEstat() {
        return estat;
    }

    @JsonProperty("estat")
    public void setEstat(String estat) {
        this.estat = estat;
    }

    @JsonProperty("missatge")
    public String getMissatge() {
        return missatge;
    }

    @JsonProperty("missatge")
    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    @JsonProperty("dataEnviament")
    public String getDataEnviament() {
        return dataEnviament;
    }

    @JsonProperty("dataEnviament")
    public void setDataEnviament(String dataEnviament) {
        this.dataEnviament = dataEnviament;
    }


}