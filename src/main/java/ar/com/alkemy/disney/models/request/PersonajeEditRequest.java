package ar.com.alkemy.disney.models.request;

import java.util.*;

import ar.com.alkemy.disney.entities.Pelicula;

public class PersonajeEditRequest {

    
    public PersonajeEditRequest(String imagen, String nombre, Integer edad, Double peso, String historia,
            List<Pelicula> peliculas) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
        this.peliculas = peliculas;
    }

    public String imagen;

    public String nombre;

    public Integer edad;

    public Double peso;

    public String historia;

    public List<Pelicula> peliculas = new ArrayList<>();
    
}
