
package org.udg.pds.cheapyandroid.entity;


import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "items",
        "metadata"
})
public class LlistaMissatges {

    @JsonProperty("items")
    private List<Missatge> items = null;
    @JsonProperty("metadata")
    private Metadata metadata;


    @JsonProperty("items")
    public List<Missatge> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Missatge> items) {
        this.items = items;
    }

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}