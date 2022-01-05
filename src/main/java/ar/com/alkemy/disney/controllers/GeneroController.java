package ar.com.alkemy.disney.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.alkemy.disney.entities.Genero;
import ar.com.alkemy.disney.models.response.GenericResponse;
import ar.com.alkemy.disney.services.GeneroService;

@RestController
public class GeneroController {
    @Autowired
    public GeneroService service;

    @PostMapping("/generos")
    public ResponseEntity<GenericResponse> postGenero(@RequestBody Genero genero) {
        GenericResponse rta = new GenericResponse();

        if (service.crear(genero)){

        rta.id = genero.getGeneroId();
        rta.isOk = true;
        rta.message = "Genero Creado con exito";
        return ResponseEntity.ok(rta);
        }else {
            rta.isOk = false;
            rta.message = "El Género ya existe";
            return ResponseEntity.badRequest().body(rta);
        }

    }

    @GetMapping("/generos")
    public ResponseEntity<List<Genero>> getGeneros(){
        return ResponseEntity.ok(service.mostrarGeneros());

    }

    

}
