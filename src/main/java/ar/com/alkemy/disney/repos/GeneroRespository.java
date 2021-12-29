package ar.com.alkemy.disney.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.alkemy.disney.entities.Genero;
@Repository
public interface GeneroRespository extends JpaRepository<Genero, Integer>{
    
    Genero findByNombre(String nombre);

    Genero findByGeneroId(Integer generoId);
}
