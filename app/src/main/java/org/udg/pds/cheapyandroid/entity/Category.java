
package org.udg.pds.cheapyandroid.entity;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "categoria"
})
public class Category {

    @JsonProperty("categoria")
    private Categoria categoria;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("categoria")
    public Categoria getCategoria() {
        return categoria;
    }

    @JsonProperty("categoria")
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


}