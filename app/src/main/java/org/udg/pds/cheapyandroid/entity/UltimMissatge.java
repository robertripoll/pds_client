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

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public UltimMissatge withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("emisor")
    public Emisor getEmisor() {
        return emisor;
    }

    @JsonProperty("emisor")
    public void setEmisor(Emisor emisor) {
        this.emisor = emisor;
    }

    public UltimMissatge withEmisor(Emisor emisor) {
        this.emisor = emisor;
        return this;
    }

    @JsonProperty("receptor")
    public Receptor getReceptor() {
        return receptor;
    }

    @JsonProperty("receptor")
    public void setReceptor(Receptor receptor) {
        this.receptor = receptor;
    }

    public UltimMissatge withReceptor(Receptor receptor) {
        this.receptor = receptor;
        return this;
    }

    @JsonProperty("estat")
    public String getEstat() {
        return estat;
    }

    @JsonProperty("estat")
    public void setEstat(String estat) {
        this.estat = estat;
    }

    public UltimMissatge withEstat(String estat) {
        this.estat = estat;
        return this;
    }

    @JsonProperty("missatge")
    public String getMissatge() {
        return missatge;
    }

    @JsonProperty("missatge")
    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    public UltimMissatge withMissatge(String missatge) {
        this.missatge = missatge;
        return this;
    }

    @JsonProperty("dataEnviament")
    public String getDataEnviament() {
        return dataEnviament;
    }

    @JsonProperty("dataEnviament")
    public void setDataEnviament(String dataEnviament) {
        this.dataEnviament = dataEnviament;
    }

    public UltimMissatge withDataEnviament(String dataEnviament) {
        this.dataEnviament = dataEnviament;
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

    public UltimMissatge withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}