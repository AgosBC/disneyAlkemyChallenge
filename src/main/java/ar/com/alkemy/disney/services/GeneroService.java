package ar.com.alkemy.disney.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disney.entities.Genero;
import ar.com.alkemy.disney.repos.GeneroRespository;

@Service
public class GeneroService {

    @Autowired
    private GeneroRespository repo;

    public boolean crear(Genero genero) {
       if (existe(genero.getNombre())) 
            return false;
        repo.save(genero);
        return true;        
        
    }

    public Genero buscarPorId (Integer id){
        return repo.findByGeneroId(id);
    }

    public List<Genero> mostrarGeneros(){
        return repo.findAll();
    }

    private boolean existe(String nombre){
        Genero genero = repo.findByNombre(nombre);
        return genero != null;
    }
    
}
