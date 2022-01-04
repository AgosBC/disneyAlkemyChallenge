package ar.com.alkemy.disney.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disney.entities.Pelicula;
import ar.com.alkemy.disney.repos.PeliculaRespository;

@Service
public class PeliculaService {

    @Autowired
    PeliculaRespository repo;

    

    public Pelicula buscarPorId(Integer id) {
        return repo.findByPeliculaId(id);
    }

    public void guardar(Pelicula pelicula) {
        repo.save(pelicula);
    }


    
}
