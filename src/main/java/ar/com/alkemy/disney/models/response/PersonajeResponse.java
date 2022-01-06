package ar.com.alkemy.disney.models.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class PersonajeResponse {
    

    public PersonajeResponse() {
    }

    public PersonajeResponse(String imagen, String nombre) {
        this.imagen = imagen;
        this.nombre = nombre;
    }
    @JsonInclude(Include.NON_NULL)
    public Integer personajeId;

    public String imagen;

    public String nombre;

    @JsonInclude(Include.NON_NULL)
    public Integer edad;
    @JsonInclude(Include.NON_NULL)
    public Double peso;
    @JsonInclude(Include.NON_NULL)
    public String historia;
}
