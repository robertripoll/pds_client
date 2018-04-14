package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Imatge {

    @JsonProperty("imatge")
    private Imatge_ imatge;
    @JsonProperty("ruta")
    private String ruta;


    @JsonProperty("imatge")
    public Imatge_ getImatge() {
        return imatge;
    }

    @JsonProperty("imatge")
    public void setImatge(Imatge_ imatge) {
        this.imatge = imatge;
    }

    @JsonProperty("ruta")
    public String getRuta() {
        return ruta;
    }

    @JsonProperty("ruta")
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return " -  " + imatge.toString();
    }
}
