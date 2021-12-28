package ar.com.alkemy.disney.entities;

import java.util.*;

import javax.persistence.*;
@Entity
@Table(name = "personaje")
public class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personaje_id")
    private Integer personajeId;

    private String imagen;

    private String nombre;

    private Integer edad;

    private Double peso;

    private String historia;

    @ManyToMany
    @JoinTable(name = "personaje_pelicula", 
            joinColumns = @JoinColumn(name = "personaje_id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id"))
    private List<Pelicula> peliculas = new ArrayList<>();

    public Integer getPersonajeId() {
        return personajeId;
    }

    public void setPersonajeId(Integer personajeId) {
        this.personajeId = personajeId;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    
    
}
