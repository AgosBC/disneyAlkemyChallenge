package ar.com.alkemy.disney.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.alkemy.disney.entities.Personaje;
import ar.com.alkemy.disney.models.request.PersonajeEditRequest;
import ar.com.alkemy.disney.models.response.GenericResponse;
import ar.com.alkemy.disney.models.response.PersonajeResponse;
import ar.com.alkemy.disney.services.PersonajeService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class PersonajeController {

    @Autowired
    PersonajeService service;

    @PostMapping("/personajes")
    public ResponseEntity<GenericResponse> postPersonajes(@RequestBody Personaje personaje) {

        GenericResponse rta = new GenericResponse();
        // if admin else forbiden
        service.crear(personaje);

        rta.id = personaje.getPersonajeId();
        rta.isOk = true;
        rta.message = " el personaje ha sido creado correctamente";

        return ResponseEntity.ok(rta);

    }

    @GetMapping(value = "/personajes")
    @ResponseBody
    public ResponseEntity<List<PersonajeResponse>> getPersonajes(@RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age) {

        List<PersonajeResponse> lista = new ArrayList<>();

        if (name == null && age == null) {
            List<Personaje> personajes = service.mostrarPersonajes();
            for (Personaje personaje : personajes) {
                PersonajeResponse pR = new PersonajeResponse(personaje.getImagen(), personaje.getNombre());
                lista.add(pR);

            }
            return ResponseEntity.ok(lista);

        } else {

            List<Personaje> personajes = service.buscar(name.toLowerCase(), age);// completar

            for (Personaje personaje : personajes) {
                PersonajeResponse pR = new PersonajeResponse();
                pR.nombre = personaje.getNombre();
                pR.imagen = personaje.getImagen();
                pR.edad = personaje.getEdad();
                pR.peso = personaje.getPeso();
                pR.historia = personaje.getHistoria();
                lista.add(pR);
            }

            /*
             * List<Personaje> personajes = service.buscarPorNombre(name.toLowerCase());
             * 
             * for (Personaje personaje : personajes) {
             * PersonajeResponse pR = new PersonajeResponse();
             * pR.nombre = personaje.getNombre();
             * pR.imagen = personaje.getImagen();
             * pR.edad = personaje.getEdad();
             * pR.peso = personaje.getPeso();
             * pR.historia = personaje.getHistoria();
             * lista.add(pR);
             * 
             * }
             */

            return ResponseEntity.ok(lista);

        }

    }

    /*
     * @GetMapping(value = "/personajes")
     * 
     * @ResponseBody
     * public ResponseEntity<List<PersonajeResponse>> getPersonajes(@RequestParam
     * Integer age) {
     * 
     * List<PersonajeResponse> lista = new ArrayList<>();
     * 
     * List<Personaje> personajes = service.buscarPorEdad(age);
     * 
     * for (Personaje personaje : personajes) {
     * PersonajeResponse pR = new PersonajeResponse();
     * pR.nombre = personaje.getNombre();
     * pR.imagen = personaje.getImagen();
     * pR.edad = personaje.getEdad();
     * pR.peso = personaje.getPeso();
     * pR.historia = personaje.getHistoria();
     * lista.add(pR);
     * 
     * }
     * 
     * return ResponseEntity.ok(lista);
     * 
     * }
     */

    @PutMapping(value = "personaje/{id}")
    public ResponseEntity<GenericResponse> modificar(@PathVariable Integer id,
            @RequestBody PersonajeEditRequest personaje) {

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
