package  ar.com.alkemy.disney.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;

import ar.com.alkemy.disney.entities.Usuario;
import ar.com.alkemy.disney.models.request.*;
import ar.com.alkemy.disney.models.response.*;
import ar.com.alkemy.disney.security.jwt.JWTTokenUtil;
import ar.com.alkemy.disney.services.JWTUserDetailsService;
import ar.com.alkemy.disney.services.UsuarioService;

@RestController
public class AuthController {

    @Autowired
    UsuarioService usuarioService;
    @Autowired
    private JWTTokenUtil jwtTokenUtil;
    @Autowired
    private JWTUserDetailsService userDetailsService;

    @PostMapping("/auth/register")
    public ResponseEntity<RegistrationResponse> postRegisterUser(@Valid @RequestBody RegistrationRequest req,
            BindingResult results) {
        RegistrationResponse r = new RegistrationResponse();
        
        if (results.hasErrors()){
            r.isOk = false;
            r.message = "Hubo errores al recibir el request";
            //recorrer cada error, crear una instancia de ErrorItemInfo y agregarlo a r.errores
            results.getFieldErrors().stream().forEach(e -> {
                r.errors.add(new ErrorItemInfo(e.getField(), e.getDefaultMessage()));
            });

            return ResponseEntity.badRequest().body(r);
           
        }
        
        Usuario usuario = usuarioService.crearUsuario(req.userType, req.name, req.lastname, req.birthDate, req.username,
        req.email, req.password);

        r.isOk = true;
        r.message = "Bienvenido! su usuario ha sido registrado con exito";
        r.userId = usuario.getUsuarioId();

        return ResponseEntity.ok(r);

    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest,
            BindingResult results) throws Exception {

        Usuario usuarioLogueado = usuarioService.login(authenticationRequest.username, authenticationRequest.password);

        UserDetails userDetails = usuarioService.getUserAsUserDetail(usuarioLogueado);
        Map<String, Object> claims = usuarioService.getUserClaims(usuarioLogueado);

        
        String token = jwtTokenUtil.generateToken(userDetails, claims);

        Usuario u = usuarioService.buscarPorUsername(authenticationRequest.username);

        LoginResponse rta = new LoginResponse();
        rta.id = u.getUsuarioId();
        rta.userType = u.getTipoUsuario();
        rta.entityId = u.obtenerEntityId();
        rta.username = authenticationRequest.username;
        rta.email = u.getEmail();
        rta.token = token;

        return ResponseEntity.ok(rta);

    }
    
    
}
