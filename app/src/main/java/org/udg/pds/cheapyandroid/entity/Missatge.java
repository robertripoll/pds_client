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
        "id_emisor",
        "nom_emisor"
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

    @JsonProperty("id_emisor")
    private Integer id_emisor;

    @JsonProperty("nom_emisor")
    private String nom_emisor;

    public Missatge() {

    }

    public Missatge(String message, Integer userID_connected, String userName_connected) {
        this.text = message;
        this.estat = "no llegit";
        this.id = -1; //quin identificado posar?
        this.dataEnviament = "data actual";
        this.id_emisor = userID_connected;
        this.nom_emisor = userName_connected;
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

    @JsonProperty("id_emisor")
    public Integer getId_emisor() {
        return id_emisor;
    }

    @JsonProperty("id_emisor")
    public void setId_emisor(Integer id_emisor) {
        this.id_emisor = id_emisor;
    }

    @JsonProperty("nom_emisor")
    public String getNom_emisor() {
        return nom_emisor;
    }

    @JsonProperty("nom_emisor")
    public void setNom_emisor(String nom_emisor) {
        this.nom_emisor = nom_emisor;
    }

}


