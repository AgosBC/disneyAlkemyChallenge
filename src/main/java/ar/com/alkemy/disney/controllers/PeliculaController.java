package ar.com.alkemy.disney.controllers;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ar.com.alkemy.disney.entities.Genero;
import ar.com.alkemy.disney.entities.Pelicula;
import ar.com.alkemy.disney.models.request.ErrorItemInfo;
import ar.com.alkemy.disney.models.request.PeliculaNuevaInfo;
import ar.com.alkemy.disney.models.response.GenericResponse;
import ar.com.alkemy.disney.models.response.PeliculaResponse;
import ar.com.alkemy.disney.services.GeneroService;
import ar.com.alkemy.disney.services.PeliculaService;

@RestController
public class PeliculaController {

    @Autowired
    PeliculaService service;

    @Autowired
    GeneroService generoService;

    @PostMapping("/movies")
    @PreAuthorize("hasAuthority('CLAIM_userType_ADMIN')")
    public ResponseEntity<GenericResponse> postPelicula(@Valid @RequestBody PeliculaNuevaInfo peliculaNueva,
            BindingResult results) {

        GenericResponse rta = new GenericResponse();

        if (results.hasErrors()) {
            rta.isOk = false;
            rta.message = "Hubo errores al recibir el request";
            results.getFieldErrors().stream().forEach(e -> {
                rta.errors.add(new ErrorItemInfo(e.getField(), e.getDefaultMessage()));
            });

            return ResponseEntity.badRequest().body(rta);
        }

        Pelicula pelicula = service.crear(peliculaNueva);

        rta.id = pelicula.getPeliculaId();
        rta.isOk = true;
        rta.message = "La pelicula ha sido creado correctamente";

        return ResponseEntity.ok(rta);

    }

    @GetMapping(value = "/movies")
    public ResponseEntity<List<PeliculaResponse>> mostrarPeliculas() {

        return ResponseEntity.ok(service.mostrarPeliculas());
    }

    @GetMapping(value = "/movies", params = "order") // muestra peli completa, todos los detalles
    public ResponseEntity<List<Pelicula>> mostrarPeliculasOrden(@RequestParam(defaultValue = "ASC") String order) {

        if (order.equalsIgnoreCase("ASC") || order.equalsIgnoreCase("DESC"))
            return ResponseEntity.ok(service.mostrarPeliculasOrden(order));

        else
            return ResponseEntity.badRequest().build();

    }

    @GetMapping(value = "/movies", params = "name")
    public ResponseEntity<List<Pelicula>> buscarPorNombre(@RequestParam String name) {

        List<Pelicula> peliculas = service.buscarPorTitulo(name);

        if (peliculas.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(peliculas);

    }

    @GetMapping(value = "/movies", params = "genre")
    public ResponseEntity<List<Pelicula>> filtrarPorGenero(@RequestParam(name = "genre") Integer idGenero) {

        List<Pelicula> peliculas = service.filtrarPorGenero(idGenero);

        return ResponseEntity.ok(peliculas);

    }

    @DeleteMapping(value = "/movie/{id}")
    @PreAuthorize("hasAuthority('CLAIM_userType_ADMIN')")
    public ResponseEntity<GenericResponse> eliminarPelicula(@PathVariable Integer id) {
        service.eliminarPelicula(id);

        GenericResponse rta = new GenericResponse();
        rta.isOk = true;
        rta.message = "esta Pelicula ha sido eliminada correctamente";

        return ResponseEntity.ok(rta);
    }

    @PutMapping("/movies/{id}")
    @PreAuthorize("hasAuthority('CLAIM_userType_ADMIN')")
    public ResponseEntity<GenericResponse> editar(@PathVariable Integer id, @RequestBody PeliculaNuevaInfo peliculaActualizada) {

        GenericResponse rta = new GenericResponse();

        Pelicula p = service.buscarPorId(id);

        if (p == null) {
            rta.isOk = false;
            rta.message = "El id ingresado no existe";
            return ResponseEntity.badRequest().body(rta);
        } else {

            p.setImagen(peliculaActualizada.imagen);
            p.setTitulo(peliculaActualizada.titulo);
            p.setFechaCreacion(peliculaActualizada.fechaCreacion);
            p.setCalificacion(peliculaActualizada.calificacion);

            Genero genero = generoService.buscarPorId(peliculaActualizada.generoId);
            genero.agregarPelicula(p);

            service.guardar(p);

            rta.isOk = true;
            rta.message = "la pel??cula se a actualizado con ??xito";
            return ResponseEntity.ok(rta);
        }
    }
}
