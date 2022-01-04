package ar.com.alkemy.disney.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.alkemy.disney.entities.Pelicula;
@Repository
public interface PeliculaRespository extends JpaRepository<Pelicula, Integer> {

    Pelicula findByPeliculaId(Integer id);
    
}
