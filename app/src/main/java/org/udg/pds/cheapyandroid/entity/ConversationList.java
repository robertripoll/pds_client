
package org.udg.pds.cheapyandroid.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "llistaConversacions",
    "metadata"
})
public class ConversationList {

    @JsonProperty("llistaConversacions")
    private List<LlistaConversacion> llistaConversacions = null;
    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("llistaConversacions")
    public List<LlistaConversacion> getLlistaConversacions() {
        return llistaConversacions;
    }

    @JsonProperty("llistaConversacions")
    public void setLlistaConversacions(List<LlistaConversacion> llistaConversacions) {
        this.llistaConversacions = llistaConversacions;
    }

    public ConversationList withLlistaConversacions(List<LlistaConversacion> llistaConversacions) {
        this.llistaConversacions = llistaConversacions;
        return this;
    }

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public ConversationList withMetadata(Metadata metadata) {
        this.metadata = metadata;
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

    public ConversationList withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
