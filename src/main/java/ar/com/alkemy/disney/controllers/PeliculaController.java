package ar.com.alkemy.disney.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.alkemy.disney.entities.Genero;
import ar.com.alkemy.disney.entities.Pelicula;
import ar.com.alkemy.disney.models.request.PeliculaNuevaInfo;
import ar.com.alkemy.disney.models.response.GenericResponse;
import ar.com.alkemy.disney.services.GeneroService;
import ar.com.alkemy.disney.services.PeliculaService;

@RestController
public class PeliculaController {

    @Autowired
    PeliculaService service;

    @Autowired
    GeneroService generoService;

    @PostMapping("/peliculas")
    public ResponseEntity<GenericResponse> postPelicula(@RequestBody PeliculaNuevaInfo peliculaNueva) {

        GenericResponse rta = new GenericResponse();

        Pelicula pelicula = service.crear(peliculaNueva);

        rta.id = pelicula.getPeliculaId();
        rta.isOk = true;
        rta.message = "La pelicula ha sido creado correctamente";

        return ResponseEntity.ok(rta);

    }

    @PutMapping("api/pelicula/{id}")
    public ResponseEntity<GenericResponse> modificar(@PathVariable Integer id,
            @RequestBody PeliculaNuevaInfo pelicula) {

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
