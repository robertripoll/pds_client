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
    private Integer id;
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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Missatge(String missatgeRebut) {
        this.id = 3;
        this.emisor = new Emisor(1, "pep", "home");
        this.receptor = new Receptor(2, "benito", "home");
        this.estat = "rebut";
        this.missatge = missatgeRebut;
        this.dataEnviament = "2018-11-10";
    }

    public Missatge(Integer id, Emisor em, Receptor rec, String estat, String missatge, String dataProva) {
        this.id = id;
        this.emisor = em;
        this.receptor = rec;
        this.estat = estat;
        this.missatge = missatge;
        this.dataEnviament = dataProva;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
