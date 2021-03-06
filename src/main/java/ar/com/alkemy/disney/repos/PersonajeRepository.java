package ar.com.alkemy.disney.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.alkemy.disney.entities.Personaje;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {
    
    List<Personaje> findAllByOrderByNombreAsc();

    List<Personaje> findByNombre(String nombre);

    List<Personaje> findByEdad(Integer edad);

    Personaje findByPersonajeId(Integer personajeId);
    
}
