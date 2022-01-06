package ar.com.alkemy.disney.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disney.entities.Genero;
import ar.com.alkemy.disney.entities.Pelicula;
import ar.com.alkemy.disney.models.request.PeliculaNuevaInfo;
import ar.com.alkemy.disney.models.response.PeliculaResponse;
import ar.com.alkemy.disney.repos.PeliculaRespository;

@Service
public class PeliculaService {

    @Autowired
    PeliculaRespository repo;

    @Autowired
    GeneroService generoService;

    public Pelicula buscarPorId(Integer id) {
        return repo.findByPeliculaId(id);
    }

    public void guardar(Pelicula pelicula) {
        repo.save(pelicula);
    }

    public List<Pelicula> buscarPorTitulo(String titulo) {
        return repo.findByTitulo(titulo.toLowerCase());
    }

    public Pelicula crear(PeliculaNuevaInfo peliculaNueva) {

        Pelicula pelicula = new Pelicula();
        pelicula.setImagen(peliculaNueva.imagen);
        pelicula.setTitulo(peliculaNueva.titulo.toLowerCase());
        pelicula.setFechaCreacion(peliculaNueva.fechaCreacion);
        pelicula.setCalificacion(peliculaNueva.calificacion);
        pelicula.setPersonajes(peliculaNueva.personajes);

        Genero genero = generoService.buscarPorId(peliculaNueva.generoId);
        genero.agregarPelicula(pelicula);

        this.guardar(pelicula);

        return pelicula;

    }

    public List<PeliculaResponse> mostrarPeliculas() {

        List<Pelicula> peliculas = repo.findAllByOrderByTituloAsc();
        List<PeliculaResponse> lista = new ArrayList<>();
        for (Pelicula pelicula : peliculas) {
            PeliculaResponse pR = new PeliculaResponse(pelicula.getImagen(), pelicula.getTitulo(), pelicula.getFechaCreacion());
            lista.add(pR);
        }
        return lista;
    }

    public List<Pelicula> mostrarPeliculasOrden(String order) {
        
        if (order.equalsIgnoreCase("DESC"))
            return repo.findAllByOrderByTituloDesc();
        
        else return repo.findAllByOrderByTituloAsc();   
    
        
    }

    public List<Pelicula> filtrarPorGenero(Integer genreId) {
        
        List<Pelicula> peliculas = repo.findAll();
        List<Pelicula> peliculaPorGenero = new ArrayList<>();
        
        Genero genero = generoService.buscarPorId(genreId);

        for (Pelicula pelicula : peliculas) {
            if (pelicula.getGenero().getNombre().equals(genero.getNombre()))
                peliculaPorGenero.add(pelicula);
            
        }

        return peliculaPorGenero;


    
    }

    

    

    

   


    
           

}
