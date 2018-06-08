package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "nom",
        "cognoms",
        "correu",
        "contrasenya",
        "sexe",
        "telefon",
        "dataNaixement",
        "ubicacio",
        "imatge"
})
public class User implements Serializable {

    public enum Sexe {
        HOME("home"), DONA("dona"), ALTRES("altres");

        private String value;

        private Sexe(String value) {
            this.value = value;
        }

        @JsonValue
        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {this.value = value;}

        @JsonCreator
        public static Sexe create(String val)
        {
            Sexe[] sexes = Sexe.values();

            for (Sexe sexe : sexes)
            {
                if (sexe.getValue().equalsIgnoreCase(val))
                    return sexe;
            }

            return ALTRES;
        }
    }

    @JsonProperty("nom")
    private String nom;
    @JsonProperty("cognoms")
    private String cognoms;
    @JsonProperty("correu")
    private String correu;
    @JsonProperty("contrasenya")
    private String contrasenya;
    @JsonProperty("sexe")
    private Sexe sexe;
    @JsonProperty("telefon")
    private String telefon;
    @JsonProperty("dataNaixement")
    private Date dataNaixement;
    @JsonProperty("ubicacio")
    private Ubicacio ubicacio;
    @JsonProperty("imatge")
    private String imatge;


    public User(String correu_, String contrasenya_, Sexe sexe_, String nom_, String cognoms_, String telefon_, Date dataNaixament_, Ubicacio ubicacio_) {
        this.correu = correu_;
        this.contrasenya = contrasenya_;
        this.sexe = sexe_;
        this.nom = nom_;
        this.cognoms = cognoms_;
        this.telefon = telefon_;
        this.dataNaixement = dataNaixament_;
        this.ubicacio = ubicacio_;
        this.imatge = "";
    }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    @JsonProperty("cognoms")
    public String getCognoms() {
        return cognoms;
    }

    @JsonProperty("cognoms")
    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    @JsonProperty("correu")
    public String getCorreu() {
        return correu;
    }

    @JsonProperty("correu")
    public void setCorreu(String correu) {
        this.correu = correu;
    }

    @JsonProperty("contrasenya")
    public String getContrasenya() {
        return contrasenya;
    }

    @JsonProperty("contrasenya")
    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @JsonProperty("sexe")
    public Sexe getSexe() {
        return sexe;
    }

    @JsonProperty("sexe")
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    @JsonProperty("telefon")
    public String getTelefon() {
        return telefon;
    }

    @JsonProperty("telefon")
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @JsonProperty("dataNaixement")
    public Date getDataNaixement() {
        return dataNaixement;
    }

    @JsonProperty("dataNaixement")
    public void setDataNaixement(Date dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    @JsonProperty("ubicacio")
    public Ubicacio getUbicacio() {
        return ubicacio;
    }

    @JsonProperty("ubicacio")
    public void setUbicacio(Ubicacio ubicacio) {
        this.ubicacio = ubicacio;
    }

    @JsonProperty("imatge")
    public String getImatge() {
        return imatge;
    }

    @JsonProperty("imatge")
    public void setImatge(String imatge) {
        this.imatge = imatge;
    }

}
