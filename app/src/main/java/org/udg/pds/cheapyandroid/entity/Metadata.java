
package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "limit",
        "currentOffset",
        "nextOffset",
        "total"
})
public class Metadata {

    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("currentOffset")
    private Integer currentOffset;
    @JsonProperty("nextOffset")
    private Integer nextOffset;
    @JsonProperty("total")
    private Integer total;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("limit")
    public Integer getLimit() {
        return limit;
    }

    @JsonProperty("limit")
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonProperty("currentOffset")
    public Integer getCurrentOffset() {
        return currentOffset;
    }

    @JsonProperty("currentOffset")
    public void setCurrentOffset(Integer currentOffset) {
        this.currentOffset = currentOffset;
    }

    @JsonProperty("nextOffset")
    public Integer getNextOffset() {
        return nextOffset;
    }

    @JsonProperty("nextOffset")
    public void setNextOffset(Integer nextOffset) {
        this.nextOffset = nextOffset;
    }

    @JsonProperty("total")
    public Integer getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(Integer total) {
        this.total = total;
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