
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
    "conversacio"
})
public class LlistaConversacion {

    @JsonProperty("conversacio")
    private Conversacio conversacio;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("conversacio")
    public Conversacio getConversacio() {
        return conversacio;
    }

    @JsonProperty("conversacio")
    public void setConversacio(Conversacio conversacio) {
        this.conversacio = conversacio;
    }

    public LlistaConversacion withConversacio(Conversacio conversacio) {
        this.conversacio = conversacio;
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

    public LlistaConversacion withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
