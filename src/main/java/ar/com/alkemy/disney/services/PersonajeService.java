package ar.com.alkemy.disney.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disney.entities.Pelicula;
import ar.com.alkemy.disney.entities.Personaje;
import ar.com.alkemy.disney.models.request.PersonajeEditRequest;
import ar.com.alkemy.disney.repos.PersonajeRepository;

@Service
public class PersonajeService {

    @Autowired
    PersonajeRepository repo;
    

    public void crear(Personaje personaje) {
        personaje.setNombre(personaje.getNombre().toLowerCase());
        repo.save(personaje);
    }

    public List<Personaje> mostrarPersonajes() {
        return repo.findAll();
    }

    public List<Personaje> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
    }

    public List<Personaje> buscarPorEdad(Integer age) {
        return repo.findByEdad(age);
    }

    public List<Personaje> buscar(String name, Integer age) {
        return buscarPorNombre(name);// completar

    }

    public Personaje buscarPorId(Integer id) {
        return repo.findByPersonajeId(id);
    }

   
    public void guardar(Personaje personaje) {
        repo.save(personaje);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }


}
