package org.udg.pds.cheapyandroid.entity;


import com.fasterxml.jackson.annotation.*;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "estrelles",
        "comentaris"
})
public class Valoracio {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("estrelles")
    private Integer estrelles;
    @JsonProperty("comentaris")
    private String comentaris;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("estrelles")
    public Integer getEstrelles() {
        return estrelles;
    }

    @JsonProperty("estrelles")
    public void setEstrelles(Integer estrelles) {
        this.estrelles = estrelles;
    }

    @JsonProperty("comentaris")
    public String getComentaris() {
        return comentaris;
    }

    @JsonProperty("comentaris")
    public void setComentaris(String comentaris) {
        this.comentaris = comentaris;
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