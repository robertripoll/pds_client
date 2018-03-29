package org.udg.pds.cheapyandroid.entity;


public class User {

    private String id;
    private String nom;
    private String cognoms;
    private String dataNaixement;
    private String correu;
    private int nombreVendes;
    private int nombreCompres;
    private int nombreValoracions;
    private double mitjanaValoracions;
    private Ubicacio ubicació;
    private String imatge;

    public User(String id, String nom, String cognoms, String dataNaixement, String correu, int nombreVendes, int nombreCompres, int nombreValoracions, double mitjanaValoracions, Ubicacio ubicació, String imatge) {
        this.id = id;
        this.nom = nom;
        this.cognoms = cognoms;
        this.dataNaixement = dataNaixement;
        this.correu = correu;
        this.nombreVendes = nombreVendes;
        this.nombreCompres = nombreCompres;
        this.nombreValoracions = nombreValoracions;
        this.mitjanaValoracions = mitjanaValoracions;
        this.ubicació = ubicació;
        this.imatge = imatge;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public String getDataNaixement() {
        return dataNaixement;
    }

    public String getCorreu() {
        return correu;
    }

    public int getNombreVendes() {
        return nombreVendes;
    }

    public int getNombreCompres() {
        return nombreCompres;
    }

    public int getValoracions() {
        return nombreValoracions;
    }

    public double getMitjanaValoracions() {
        return mitjanaValoracions;
    }

    public Ubicacio getUbicació() {
        return ubicació;
    }

    public String getImatge() {
        return imatge;
    }
}


/*import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "usuari"
})
public class User {

    @JsonProperty("usuari")
    private User usuari;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("usuari")
    public User getUsuari() {
        return usuari;
    }

    @JsonProperty("usuari")
    public void setUsuari(User usuari) {
        this.usuari = usuari;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
*/