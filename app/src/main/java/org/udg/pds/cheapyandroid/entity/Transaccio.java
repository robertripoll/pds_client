package org.udg.pds.cheapyandroid.entity;


import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "comprador",
        "venedor",
        "valoracioComprador",
        "valoracioVenedor"
})
public class Transaccio implements Serializable{

    @JsonProperty("id")
    private Long id;
    @JsonProperty("comprador")
    private Comprador comprador;
    @JsonProperty("venedor")
    private Venedor venedor;
    @JsonProperty("valoracioComprador")
    private Valoracio valoracioComprador;
    @JsonProperty("valoracioVenedor")
    private Valoracio valoracioVenedor;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("comprador")
    public Comprador getComprador() {
        return comprador;
    }

    @JsonProperty("comprador")
    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    @JsonProperty("venedor")
    public Venedor getVenedor() {
        return venedor;
    }

    @JsonProperty("venedor")
    public void setVenedor(Venedor venedor) {
        this.venedor = venedor;
    }

    @JsonProperty("valoracioComprador")
    public Valoracio getValoracioComprador() {
        return valoracioComprador;
    }

    @JsonProperty("valoracioComprador")
    public void setValoracioComprador(Valoracio valoracioComprador) {
        this.valoracioComprador = valoracioComprador;
    }

    @JsonProperty("valoracioVenedor")
    public Valoracio getValoracioVenedor() {
        return valoracioVenedor;
    }

    @JsonProperty("valoracioVenedor")
    public void setValoracioVenedor(Valoracio valoracioVenedor) {
        this.valoracioVenedor = valoracioVenedor;
    }

}