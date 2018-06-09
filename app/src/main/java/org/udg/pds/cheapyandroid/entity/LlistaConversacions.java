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
        "items",
        "metadata"
})
public class LlistaConversacions {

    @JsonProperty("items")
    private List<ConversacioChat> items = null;
    @JsonProperty("metadata")
    private Metadata metadata;

    @JsonProperty("items")
    public List<ConversacioChat> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<ConversacioChat> items) {
        this.items = items;
    }

    public LlistaConversacions withItems(List<ConversacioChat> items) {
        this.items = items;
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

    public LlistaConversacions withMetadata(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

}