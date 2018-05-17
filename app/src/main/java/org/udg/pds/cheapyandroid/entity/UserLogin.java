package org.udg.pds.cheapyandroid.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "correu",
        "contrasenya"
})

public class UserLogin {

    @JsonProperty("correu")
    private String correu;

    @JsonProperty("contrasenya")
    private String contrasenya;

    public UserLogin(String correu, String contrasenya) {
        this.correu = correu;
        this.contrasenya = contrasenya;
    }


    @JsonProperty("correu")
    public String getCorreu() {
        return correu;
    }

    @JsonProperty("correu")
    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public UserLogin withCorreu(String correu) {
        this.correu = correu;
        return this;
    }

    @JsonProperty("contrasenya")
    public String getContrasenya() {
        return contrasenya;
    }

    @JsonProperty("contrasenya")
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public UserLogin withContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
        return this;
    }
}
