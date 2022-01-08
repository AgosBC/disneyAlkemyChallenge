package ar.com.alkemy.disney.models.request;

import javax.validation.constraints.*;
import java.util.*;

public class PeliculaNuevaInfo {

    @NotBlank(message = "La imagen no puede ser nula o vacia")
    public String imagen;

    @NotBlank(message = "el titulo no puede ser nulo o vacio")
    public String titulo;

    public Date fechaCreacion;

    @Min(1)
    @Max(5)
    public double calificacion;

    @Positive(message = "El genero id debe ser positivo")
    public Integer generoId;

}
