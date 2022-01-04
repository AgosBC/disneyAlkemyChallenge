package ar.com.alkemy.disney.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.alkemy.disney.entities.Genero;
import ar.com.alkemy.disney.entities.Pelicula;
import ar.com.alkemy.disney.models.request.PeliculaNuevaRequest;
import ar.com.alkemy.disney.models.response.GenericResponse;
import ar.com.alkemy.disney.services.GeneroService;
import ar.com.alkemy.disney.services.PeliculaService;

@RestController
public class PeliculaController {

    @Autowired
    PeliculaService service;

    @Autowired
    GeneroService generoService;

    @PostMapping("api/pelicula")
    public ResponseEntity<GenericResponse> postPersonajes(@RequestBody PeliculaNuevaRequest pR) {

        GenericResponse rta = new GenericResponse();

        Pelicula pelicula = new Pelicula();

        pelicula.setImagen(pR.imagen);
        pelicula.setTitulo(pR.titulo);
        pelicula.setFechaCreacion(pR.fechaCreacion);
        pelicula.setCalificacion(pR.calificacion);
        pelicula.setPersonajes(pR.personajes);
        
        Genero genero = generoService.buscarPorId(pR.generoId);
        genero.agregarPelicula(pelicula);

        service.guardar(pelicula);

        rta.id = pelicula.getPeliculaId();
        rta.isOk = true;
        rta.message = "La pelicula ha sido creado correctamente";

        return ResponseEntity.ok(rta);

    }

    @PutMapping("api/pelicula/{id}")
    public ResponseEntity<GenericResponse> modificar(@PathVariable Integer id,
            @RequestBody PeliculaNuevaRequest pelicula) {

        GenericResponse rta = new GenericResponse();

        Pelicula peliculaActualizado = service.buscarPorId(id);

        if (id == null) {
            rta.isOk = false;
            rta.message = "No existe Pelicula con ese id";

            return ResponseEntity.badRequest().body(rta);

        } else {
            peliculaActualizado.setImagen(pelicula.imagen);
            peliculaActualizado.setTitulo(pelicula.titulo);
            peliculaActualizado.setFechaCreacion(pelicula.fechaCreacion);
            peliculaActualizado.setCalificacion(pelicula.calificacion);
            peliculaActualizado.setPersonajes(pelicula.personajes);

            Genero genero = generoService.buscarPorId(pelicula.generoId);
            genero.agregarPelicula(peliculaActualizado);

            service.guardar(peliculaActualizado);
            

            rta.isOk = true;
            rta.message = "El personaje ha sido actualizado";

            return ResponseEntity.ok(rta);
        }

    }

}
