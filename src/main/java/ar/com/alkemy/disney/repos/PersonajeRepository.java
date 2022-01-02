package ar.com.alkemy.disney.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.alkemy.disney.entities.Personaje;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {

    List<Personaje> findByNombre(String nombre);
    
}
