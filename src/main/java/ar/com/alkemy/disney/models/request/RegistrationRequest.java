package ar.com.alkemy.disney.models.request;

import java.util.Date;

import javax.validation.constraints.*;

import ar.com.alkemy.disney.entities.Usuario.TipoUsuarioEnum;

public class RegistrationRequest {

    @NotBlank(message = "el nombre no puede ser nulo o vacio")
    public String name;

    @NotBlank(message = "el apellido no puede ser nulo o vacio")
    public String lastname;

    @Past(message = "la fecha de nacimiento no puede ser a futuro")
    public Date birthDate;

    @Email(message = "el email es invalido")
    public String email;

    @NotBlank(message =  "el usuario no puede estar vacio")
    @Size(min = 6, max = 20, message = "El usuario debe tener entre 8 y 20 caracteres")
    public String username;

    public TipoUsuarioEnum userType;

    @NotBlank(message =  "la contraseña no puede ser vacia")
    @Size(min = 6, max = 16, message = "La contraseña debe tener entre 6 y 16 caracteres")
    public String password;
    
}
