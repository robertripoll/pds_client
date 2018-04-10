package org.udg.pds.cheapyandroid.entity;

import java.util.Date;
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
        "nom",
        "cognoms",
        "dataNaixement",
        "correu",
        "nombreVendes",
        "nombreCompres",
        "nombreValoracions",
        "mitjanaValoracions",
        "ubicacio",
        "imatgeURL"
})
public class User {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("cognoms")
    private String cognoms;
    @JsonProperty("telefon")
    private Integer telefon;
    @JsonProperty("dataNaixement")
    private String dataNaixement;
    @JsonProperty("correu")
    private String correu;
    @JsonProperty("contrasenya")
    private String contrasenya;
    @JsonProperty("sexe")
    private String sexe;
    @JsonProperty("nombreVendes")
    private Integer nombreVendes;
    @JsonProperty("nombreCompres")
    private Integer nombreCompres;
    @JsonProperty("nombreValoracions")
    private Integer nombreValoracions;
    @JsonProperty("mitjanaValoracions")
    private Double mitjanaValoracions;
    @JsonProperty("ubicacio")
    private Ubicacio ubicacio;
    @JsonProperty("imatgeURL")
    private String imatgeURL;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public User(String correu, String contrasenya, String sexe, String nom, String cognoms, int telefon, String dataNaixement) {

        this.correu = correu;
        this.contrasenya = contrasenya;
        this.sexe = sexe;
        this.nom = nom;
        this.cognoms = cognoms;
        this.telefon = telefon;
        this.dataNaixement = dataNaixement;

        this.id = 0;
        this.nombreVendes = 0;
        this.nombreCompres = 0;
        this.nombreValoracions = 0;
        this.mitjanaValoracions = 0.0;
        this.imatgeURL = "";
        this.ubicacio = new Ubicacio();
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public User withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("nom")
    public String getNom() {
        return nom;
    }

    @JsonProperty("nom")
    public void setNom(String nom) {
        this.nom = nom;
    }

    public User withNom(String nom) {
        this.nom = nom;
        return this;
    }

    @JsonProperty("cognoms")
    public String getCognoms() {
        return cognoms;
    }

    @JsonProperty("cognoms")
    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public User withCognoms(String cognoms) {
        this.cognoms = cognoms;
        return this;
    }

    @JsonProperty("dataNaixement")
    public String getDataNaixement() {
        return dataNaixement;
    }

    @JsonProperty("dataNaixement")
    public void setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public User withDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
        return this;
    }

    @JsonProperty("correu")
    public String getCorreu() {
        return correu;
    }

    @JsonProperty("correu")
    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public User withCorreu(String correu) {
        this.correu = correu;
        return this;
    }

    @JsonProperty("nombreVendes")
    public Integer getNombreVendes() {
        return nombreVendes;
    }

    @JsonProperty("nombreVendes")
    public void setNombreVendes(Integer nombreVendes) {
        this.nombreVendes = nombreVendes;
    }

    public User withNombreVendes(Integer nombreVendes) {
        this.nombreVendes = nombreVendes;
        return this;
    }

    @JsonProperty("nombreCompres")
    public Integer getNombreCompres() {
        return nombreCompres;
    }

    @JsonProperty("nombreCompres")
    public void setNombreCompres(Integer nombreCompres) {
        this.nombreCompres = nombreCompres;
    }

    public User withNombreCompres(Integer nombreCompres) {
        this.nombreCompres = nombreCompres;
        return this;
    }

    @JsonProperty("nombreValoracions")
    public Integer getNombreValoracions() {
        return nombreValoracions;
    }

    @JsonProperty("nombreValoracions")
    public void setNombreValoracions(Integer nombreValoracions) {
        this.nombreValoracions = nombreValoracions;
    }

    public User withNombreValoracions(Integer nombreValoracions) {
        this.nombreValoracions = nombreValoracions;
        return this;
    }

    @JsonProperty("mitjanaValoracions")
    public Double getMitjanaValoracions() {
        return mitjanaValoracions;
    }

    @JsonProperty("mitjanaValoracions")
    public void setMitjanaValoracions(Double mitjanaValoracions) {
        this.mitjanaValoracions = mitjanaValoracions;
    }

    public User withMitjanaValoracions(Double mitjanaValoracions) {
        this.mitjanaValoracions = mitjanaValoracions;
        return this;
    }

    @JsonProperty("ubicacio")
    public Ubicacio getUbicacio() {
        return ubicacio;
    }

    @JsonProperty("ubicacio")
    public void setUbicacio(Ubicacio ubicacio) {
        this.ubicacio = ubicacio;
    }

    public User withUbicacio(Ubicacio ubicacio) {
        this.ubicacio = ubicacio;
        return this;
    }

    @JsonProperty("imatgeURL")
    public String getImatgeURL() {
        return imatgeURL;
    }

    @JsonProperty("imatgeURL")
    public void setImatgeURL(String imatgeURL) {
        this.imatgeURL = imatgeURL;
    }

    public User withImatgeURL(String imatgeURL) {
        this.imatgeURL = imatgeURL;
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

    public User withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @JsonProperty("telefon")
    public Integer getTelefon() {
        return telefon;
    }

    @JsonProperty("telefon")
    public void setTelefon(Integer telefon) {
        this.telefon = telefon;
    }

    public User withTelefon(Integer telefon) {
        this.telefon = telefon;
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

    public User withContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
        return this;
    }

    @JsonProperty("sexe")
    public String getSexe() {
        return sexe;
    }

    @JsonProperty("sexe")
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public User withSexe(String sexe) {
        this.sexe = sexe;
        return this;
    }
}