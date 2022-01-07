package ar.com.alkemy.disney.models.response;

import ar.com.alkemy.disney.entities.Usuario.TipoUsuarioEnum;

public class LoginResponse {
    
    public Integer id;
    public String username;
    public String token;
    public String email;
    public TipoUsuarioEnum userType;
    public Integer entityId; //1admin 2visitante
    
}
