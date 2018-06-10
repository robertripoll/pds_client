package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "emisor",
        "receptor",
        "estat",
        "missatge",
        "dataEnviament"
})
public class Missatge {

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

    public Missatge(Long id, Emisor em, Receptor rec, String estat, String missatge, String dataProva) {
        this.id = id;
        this.emisor = em;
        this.receptor = rec;
        this.estat = estat;
        this.missatge = missatge;
        this.dataEnviament = dataProva;
    }

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
