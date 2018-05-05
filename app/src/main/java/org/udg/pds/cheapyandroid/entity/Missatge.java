package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "text",
        "estat",
        "dataEnviament",
        "emisor"
})

public class Missatge {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("text")
    private String text;

    @JsonProperty("estat")
    private String estat;

    @JsonProperty("dataEnviament")
    private String dataEnviament;

    @JsonProperty("emisor")
    private Emisor emisor;

    public Missatge(String message, Emisor em) {
        this.text = message;
        this.estat = "no llegit";
        this.id = -1; //quin identificado posar?
        this.dataEnviament = "data actual";
        this.emisor = em;
    }

    public Missatge() {

    }


    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("estat")
    public String getEstat() {
        return estat;
    }

    @JsonProperty("estat")
    public void setEstat(String estat) {
        this.estat = estat;
    }

    @JsonProperty("dataEnviament")
    public String getDataEnviament() {
        return dataEnviament;
    }

    @JsonProperty("dataEnviament")
    public void setDataEnviament(String dataEnviament) {
        this.dataEnviament = dataEnviament;
    }

    @JsonProperty("emisor")
    public Emisor getEmisor() {
        return emisor;
    }

    @JsonProperty("emisor")
    public void setEmisor(Emisor emisor) {
        this.emisor = emisor;
    }

}


