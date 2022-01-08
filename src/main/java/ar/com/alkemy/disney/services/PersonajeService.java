package ar.com.alkemy.disney.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disney.entities.Pelicula;
import ar.com.alkemy.disney.entities.Personaje;
import ar.com.alkemy.disney.models.request.PersonajeNuevoInfo;
import ar.com.alkemy.disney.models.response.PersonajeResponse;
import ar.com.alkemy.disney.repos.PersonajeRepository;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository repo;

    @Autowired 
    private PeliculaService peliculaService;

    

    public Personaje crear(PersonajeNuevoInfo personajeNuevo) {

        Personaje personaje = new Personaje();
        personaje.setImagen(personajeNuevo.imagen);
        personaje.setNombre(personajeNuevo.nombre.toLowerCase());
        personaje.setEdad(personajeNuevo.edad);
        personaje.setPeso(personajeNuevo.peso);
        personaje.setHistoria(personajeNuevo.historia);
       
        return repo.save(personaje);
    }

    public List<Personaje> mostrarPersonajes() {
        return repo.findAllByOrderByNombreAsc();
    }

    public List<Personaje> buscarPorNombre(String nombre) {
        return repo.findByNombre(nombre);
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

    public List<PersonajeResponse> filtrarPorEdad(Integer edad) {
        
        List<Personaje>personajes = repo.findByEdad(edad);
        List<PersonajeResponse> listaFiltrada = new ArrayList<>();

        for (Personaje personaje : personajes) {
            PersonajeResponse pR = new PersonajeResponse(personaje.getImagen(), personaje.getNombre());
            listaFiltrada.add(pR);
            
        }

        return listaFiltrada;
    }

    public List<PersonajeResponse> mostrarPersonajesDePelicula(Integer idMovie) {
        
        Pelicula pelicula = peliculaService.buscarPorId(idMovie);

        List<Personaje> personajes = pelicula.getPersonajes();
        List<PersonajeResponse> personajesResponse = new ArrayList<>();
        
        for (Personaje personaje : personajes) {
            PersonajeResponse pR = new PersonajeResponse(personaje.getImagen(),personaje.getNombre());
            personajesResponse.add(pR);
            
        }
        return personajesResponse;
    }

    public void agregarPeliculasAPersonaje(Integer personajeId, List<Integer> peliculaId){

        Personaje personaje = this.buscarPorId(personajeId);
        List<Pelicula> peliculas = new ArrayList<>();

        for (Integer id : peliculaId) {

            Pelicula pelicula = peliculaService.buscarPorId(id);
            peliculas.add(pelicula);
            
        }
        personaje.setPeliculas(peliculas);
        repo.save(personaje);
    }

    


}
