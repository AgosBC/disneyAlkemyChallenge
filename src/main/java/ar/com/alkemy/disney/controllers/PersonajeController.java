package ar.com.alkemy.disney.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.alkemy.disney.entities.Personaje;
import ar.com.alkemy.disney.models.request.PersonajeNuevoInfo;
import ar.com.alkemy.disney.models.response.GenericResponse;
import ar.com.alkemy.disney.models.response.PersonajeResponse;
import ar.com.alkemy.disney.services.PersonajeService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PersonajeController {

    @Autowired
    PersonajeService service;

    @PostMapping(value = "/characters")
    public ResponseEntity<GenericResponse> postPersonajes(@RequestBody PersonajeNuevoInfo personajeNuevo) {

        GenericResponse rta = new GenericResponse();
        // if admin else forbiden
        Personaje personaje = service.crear(personajeNuevo);

        rta.id = personaje.getPersonajeId();
        rta.isOk = true;
        rta.message = " el personaje ha sido creado correctamente";

        return ResponseEntity.ok(rta);

    }

    @GetMapping(value = "/characters")
    public ResponseEntity<List<PersonajeResponse>> getPersonajes(){

        List<Personaje> personajes = service.mostrarPersonajes();
        List<PersonajeResponse> lista = new ArrayList<>();

        for (Personaje personaje : personajes) {
            PersonajeResponse pR = new PersonajeResponse(personaje.getImagen(), personaje.getNombre());
            lista.add(pR);

        }

        return ResponseEntity.ok(lista);

    }

    @GetMapping(value = "/characters", params = "name")
    public ResponseEntity<List<Personaje>> getPersonaje(@RequestParam String name) { //busqueda por nombre da el objeto personaje completo

        List<Personaje> personajes = service.buscarPorNombre(name);     
                  
        if(personajes.isEmpty())
            return ResponseEntity.noContent().build();
        
        return ResponseEntity.ok(personajes);
        

    }
    @GetMapping(value = "/caracters", params = "age")
    public ResponseEntity<List<PersonajeResponse>> filtarPorEdad(@RequestParam Integer age){
        
        return ResponseEntity.ok(service.filtrarPorEdad(age));
        
    }

    @GetMapping(value = "/characters", params = "movies")
    public ResponseEntity<List<PersonajeResponse>> mostrarPersonajesDePelicula(@RequestParam(name = "movies")Integer idMovie){

        return ResponseEntity.ok(service.mostrarPersonajesDePelicula(idMovie));
    }

    @PutMapping(value = "personaje/{id}")
    public ResponseEntity<GenericResponse> modificar(@PathVariable Integer id,
            @RequestBody PersonajeNuevoInfo personaje) {

        GenericResponse rta = new GenericResponse();

        Personaje personajeActualizado = service.buscarPorId(id);

        if (id == null) {
            rta.isOk = false;
            rta.message = "No existe Personaje con ese id";

            return ResponseEntity.badRequest().body(rta);

        } else {
            personajeActualizado.setImagen(personaje.imagen);
            personajeActualizado.setNombre(personaje.nombre);
            personajeActualizado.setEdad(personaje.edad);
            personajeActualizado.setPeso(personaje.peso);
            personajeActualizado.setHistoria(personaje.historia);
            personajeActualizado.setPeliculas(personaje.peliculas);
            service.guardar(personajeActualizado);

            rta.isOk = true;
            rta.message = "El personaje ha sido actualizado";

            return ResponseEntity.ok(rta);
        }

    }

    @DeleteMapping("personajes/{id}")
    public ResponseEntity<GenericResponse> eliminar(@PathVariable Integer id) {

        GenericResponse rta = new GenericResponse();

        if(service.buscarPorId(id) == null){
            rta.isOk = false;
            rta.message = "El id ingresado no existe";
            return ResponseEntity.badRequest().body(rta);

        } else {
            service.eliminar(id);

            rta.isOk = true;
            rta.message = "El personaje ha sido eliminado";
            return ResponseEntity.ok(rta);
        }

    }

}
