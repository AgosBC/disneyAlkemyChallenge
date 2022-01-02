package ar.com.alkemy.disney.controllers;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.alkemy.disney.entities.Personaje;
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
    public ResponseEntity<GenericResponse> postPersonajes(@RequestBody Personaje personaje){

        GenericResponse rta = new GenericResponse();
        // if admin else forbiden
        service.crear(personaje);

        rta.id = personaje.getPersonajeId();
        rta.isOk = true;
        rta.message = " el personaje ha sido creado correctamente";

        return ResponseEntity.ok(rta);
        
        
    }

    @GetMapping(value="/personajes")
    @ResponseBody
    public ResponseEntity<List<PersonajeResponse>> getPersonajes(@RequestParam(required = false) String nombre) {
        
        
        List<PersonajeResponse> lista = new ArrayList<>();

        if(nombre == null){
            List<Personaje> personajes = service.mostrarPersonajes();
            for (Personaje personaje : personajes) {
                PersonajeResponse pR = new PersonajeResponse(personaje.getImagen(), personaje.getNombre());
                lista.add(pR);
                                
            }
            return ResponseEntity.ok(lista);
            
        }else {
            
            List<Personaje> personajes = service.buscarPorNombre(nombre.toLowerCase());

            for (Personaje personaje : personajes) {
                PersonajeResponse pR = new PersonajeResponse();
                pR.nombre = personaje.getNombre();
                pR.imagen = personaje.getImagen();
                pR.edad = personaje.getEdad();
                pR.peso = personaje.getPeso();
                pR.historia = personaje.getHistoria();
                lista.add(pR);
                
            }

            return ResponseEntity.ok(lista);

        }
        

        
        
        
    }

    
    
    
}
