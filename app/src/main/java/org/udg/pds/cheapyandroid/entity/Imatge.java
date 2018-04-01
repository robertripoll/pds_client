package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Imatge {



    @JsonProperty("ruta")
    private String ruta;


    @JsonProperty("ruta")
    public String getRuta() {
        return ruta;
    }

    @JsonProperty("ruta")
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }



}
