package ar.com.alkemy.disney.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.alkemy.disney.entities.Pelicula;
@Repository
public interface PeliculaRespository extends JpaRepository<Pelicula, Integer> {

    Pelicula findByPeliculaId(Integer id);
    List<Pelicula> findByTitulo(String title);
        
    List<Pelicula> findAllByOrderByTituloAsc();
    //@Query ("SELECT p FROM Pelicula p ORDER BY p.titulo DESC")
    List<Pelicula> findAllByOrderByTituloDesc();
}
