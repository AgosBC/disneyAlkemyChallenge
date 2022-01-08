package ar.com.alkemy.disney.models.request;


public class PersonajeNuevoInfo {

    
    public PersonajeNuevoInfo(String imagen, String nombre, Integer edad, Double peso, String historia) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.historia = historia;
       
    }

    public String imagen;

    public String nombre;

    public Integer edad;

    public Double peso;

    public String historia;

    
    
}
