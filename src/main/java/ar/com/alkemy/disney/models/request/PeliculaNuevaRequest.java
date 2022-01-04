package ar.com.alkemy.disney.models.request;

import java.util.*;

import ar.com.alkemy.disney.entities.Genero;
import ar.com.alkemy.disney.entities.Personaje;

public class PeliculaNuevaRequest {

    public String imagen;

    public String titulo;

    public Date fechaCreacion;

    public Integer calificacion;

    public Integer generoId;

    public List<Personaje> personajes = new ArrayList<>();

    
}
