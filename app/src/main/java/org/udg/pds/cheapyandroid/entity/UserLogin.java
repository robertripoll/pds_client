package org.udg.pds.cheapyandroid.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "usuari",
        "contrasenya"
})

public class UserLogin {

    @JsonProperty("usuari")
    private String usuari;

    @JsonProperty("contrasenya")
    private String contrasenya;

    public UserLogin(String username, String password) {
        this.usuari = username;
        this.contrasenya = password;
    }


    @JsonProperty("usuari")
    public String getUsuari() {
        return usuari;
    }

    @JsonProperty("usuari")
    public void setUsari(String usuari) {
        this.usuari = usuari;
    }

    public UserLogin withUsuari(String usuari) {
        this.usuari = usuari;
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
