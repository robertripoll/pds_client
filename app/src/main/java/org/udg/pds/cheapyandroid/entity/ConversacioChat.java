package org.udg.pds.cheapyandroid.entity;

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
            "nombreMissatges",
            "missatgesPerLlegir",
            "ultimMissatge",
            "producte",
            "usuari"
    })
    public class ConversacioChat {

        @JsonProperty("id")
        private Integer id;
        @JsonProperty("nombreMissatges")
        private Integer nombreMissatges;
        @JsonProperty("missatgesPerLlegir")
        private Boolean missatgesPerLlegir;
        @JsonProperty("ultimMissatge")
        private String ultimMissatge;
        @JsonProperty("producte")
        private Producte producte;
        @JsonProperty("usuari")
        private User usuari;
        @JsonIgnore
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        @JsonProperty("id")
        public Integer getId() {
            return id;
        }

        @JsonProperty("id")
        public void setId(Integer id) {
            this.id = id;
        }

        public ConversacioChat withId(Integer id) {
            this.id = id;
            return this;
        }

        @JsonProperty("nombreMissatges")
        public Integer getNombreMissatges() {
            return nombreMissatges;
        }

        @JsonProperty("nombreMissatges")
        public void setNombreMissatges(Integer nombreMissatges) {
            this.nombreMissatges = nombreMissatges;
        }

        public ConversacioChat withNombreMissatges(Integer nombreMissatges) {
            this.nombreMissatges = nombreMissatges;
            return this;
        }

        @JsonProperty("missatgesPerLlegir")
        public Boolean getMissatgesPerLlegir() {
            return missatgesPerLlegir;
        }

        @JsonProperty("missatgesPerLlegir")
        public void setMissatgesPerLlegir(Boolean missatgesPerLlegir) {
            this.missatgesPerLlegir = missatgesPerLlegir;
        }

        public ConversacioChat withMissatgesPerLlegir(Boolean missatgesPerLlegir) {
            this.missatgesPerLlegir = missatgesPerLlegir;
            return this;
        }

        @JsonProperty("ultimMissatge")
        public String getUltimMissatge() {
            return ultimMissatge;
        }

        @JsonProperty("ultimMissatge")
        public void setUltimMissatge(String ultimMissatge) {
            this.ultimMissatge = ultimMissatge;
        }

        public ConversacioChat withUltimMissatge(String ultimMissatge) {
            this.ultimMissatge = ultimMissatge;
            return this;
        }

        @JsonProperty("producte")
        public Producte getProducte() {
            return producte;
        }

        @JsonProperty("producte")
        public void setProducte(Producte producte) {
            this.producte = producte;
        }

        public ConversacioChat withProducte(Producte producte) {
            this.producte = producte;
            return this;
        }

        @JsonProperty("usuari")
        public User getUsuari() {
            return usuari;
        }

        @JsonProperty("usuari")
        public void setUsuari(User usuari) {
            this.usuari = usuari;
        }

        public ConversacioChat withUsuari(User usuari) {
            this.usuari = usuari;
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

        public ConversacioChat withAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
            return this;
        }

    }
