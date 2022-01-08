package ar.com.alkemy.disney.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disney.entities.Visitante;
import ar.com.alkemy.disney.repos.VisitanteRepository;
@Service
public class VisitanteService {

    @Autowired
    VisitanteRepository repo;

    public void crearVisitante(Visitante visitante) {
        repo.save(visitante);
    }
    
}
