package ar.com.alkemy.disney.models.response;

import java.util.Date;

public class PeliculaResponse {   

    public PeliculaResponse(String img, String titulo, Date fechaCreacion) {
        this.img = img;
        this.titulo = titulo;
        this.fechaCreacion = fechaCreacion;
    }
    
    public String img;

    public String titulo;

    public Date fechaCreacion;
    
}
