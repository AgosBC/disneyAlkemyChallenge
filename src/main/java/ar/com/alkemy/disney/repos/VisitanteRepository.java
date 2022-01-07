package ar.com.alkemy.disney.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.alkemy.disney.entities.Visitante;
@Repository
public interface VisitanteRepository extends JpaRepository<Visitante, Integer> {
    
}
