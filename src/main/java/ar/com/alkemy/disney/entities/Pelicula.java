package ar.com.alkemy.disney.entities;



import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pelicula")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pelicula_id")
    private Integer peliculaId;

    private String imagen;

    private String titulo;

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    private double calificacion;

    @ManyToOne
    @JoinColumn(name = "genero_id", referencedColumnName = "genero_id")
    private Genero genero;

    @JsonIgnore
    @ManyToMany(mappedBy = "peliculas", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Personaje> personajes = new ArrayList<>();

    public Integer getPeliculaId() {
        return peliculaId;
    }

    public void setPeliculaId(Integer peliculaId) {
        this.peliculaId = peliculaId;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }

    
    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
                
    }

    


    
    
}
