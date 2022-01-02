package ar.com.alkemy.disney.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import ar.com.alkemy.disney.entities.Personaje;
import ar.com.alkemy.disney.models.response.PersonajeResponse;
import ar.com.alkemy.disney.repos.PersonajeRepository;

@Service
public class PersonajeService {

    
    @Autowired
    PersonajeRepository repo;
    public Object mostrarPersonajes; 
    
    public void crear(Personaje personaje) {

        repo.save(personaje);
    }

    public List<Personaje> mostrarPersonajes() {
        return repo.findAll();
    }

    public List<Personaje> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }
    
}
