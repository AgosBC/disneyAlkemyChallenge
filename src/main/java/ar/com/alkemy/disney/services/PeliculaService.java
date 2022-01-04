package ar.com.alkemy.disney.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disney.entities.Genero;
import ar.com.alkemy.disney.entities.Pelicula;
import ar.com.alkemy.disney.models.request.PeliculaNuevaInfo;
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
    public Pelicula buscarPorTitulo(String titulo){
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



    
}
