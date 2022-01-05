package ar.com.alkemy.disney.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.alkemy.disney.entities.Genero;
import ar.com.alkemy.disney.entities.Pelicula;
import ar.com.alkemy.disney.models.request.PeliculaNuevaInfo;
import ar.com.alkemy.disney.models.response.GenericResponse;
import ar.com.alkemy.disney.models.response.PeliculaResponse;
import ar.com.alkemy.disney.services.GeneroService;
import ar.com.alkemy.disney.services.PeliculaService;

@RestController
//@RequestMapping("/movies")
public class PeliculaController {

    @Autowired
    PeliculaService service;

    @Autowired
    GeneroService generoService;

    @PostMapping("/movies")
    public ResponseEntity<GenericResponse> postPelicula(@RequestBody PeliculaNuevaInfo peliculaNueva) {

        GenericResponse rta = new GenericResponse();

        Pelicula pelicula = service.crear(peliculaNueva);

        rta.id = pelicula.getPeliculaId();
        rta.isOk = true;
        rta.message = "La pelicula ha sido creado correctamente";

        return ResponseEntity.ok(rta);

    }

    @PutMapping("/movies/{id}")
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

    @GetMapping(value = "/movies")
    public ResponseEntity<List<PeliculaResponse>> mostrarPeliculas(){

        return ResponseEntity.ok(service.mostrarPeliculas());
    }

    @GetMapping(value ="/movies", params = "order")
    public ResponseEntity<List<Pelicula>> mostrarPeliculasOrden(@RequestParam String order) {

        if(order.equalsIgnoreCase("ASC") || order.equalsIgnoreCase("DESC"))
            return ResponseEntity.ok(service.mostrarPeliculasOrden(order));       

        else return ResponseEntity.badRequest().build();
       

    }

    @GetMapping(value ="/movies", params = "name")
    public ResponseEntity<List<Pelicula>> buscarPeliculasPorNombre(@RequestParam String name) {

        List<Pelicula> peliculas = service.buscarPorTitulo(name);

        if(peliculas.isEmpty())
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(peliculas);
       

    }


    /*@GetMapping("/movies")
    public ResponseEntity<List<Pelicula>> buscarPorNombre(@RequestParam String name){
                
        List<Pelicula> peliculas = service.buscarPorTitulo(name);

        if(peliculas.equals(null))
            return ResponseEntity.notFound().build();
        
            return ResponseEntity.ok(peliculas);


            @RequestParam(required = false) String name, @RequestParam(required = false) Genre genero)

    }*/

}
