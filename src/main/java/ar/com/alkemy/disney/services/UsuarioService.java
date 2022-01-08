package ar.com.alkemy.disney.services;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ar.com.alkemy.disney.entities.*;


import ar.com.alkemy.disney.entities.Usuario.TipoUsuarioEnum;
import ar.com.alkemy.disney.repos.UsuarioRepository;
import ar.com.alkemy.disney.security.Crypto;
import ar.com.alkemy.disney.sistema.com.EmailService;

@Service
public class UsuarioService {

  @Autowired
  VisitanteService visitanteService;
  @Autowired
  AdminService adminService;
  @Autowired
  UsuarioRepository usuarioRepository;
  @Autowired
  EmailService emailService;

  public Usuario buscarPorUsername(String username) {
    return usuarioRepository.findByUsername(username);
  }

  public Usuario login(String username, String password) {

    /**
     * Metodo IniciarSesion recibe usuario y contraseña validar usuario y contraseña
     */

    Usuario u = buscarPorUsername(username);

    if (u == null || !u.getPassword().equals(Crypto.encrypt(password, u.getUsername()))) {

      throw new BadCredentialsException("Usuario o contraseña invalida");
    }

    return u;
  }
  

  public Usuario crearUsuario(TipoUsuarioEnum tipoUsuario, String nombre, String apellido, Date fechaNacimiento, String username,
                               String email, String password) {
    Usuario usuario = new Usuario();
    usuario.setUsername(username);
    usuario.setEmail(email);
    usuario.setPassword(Crypto.encrypt(password, username));
    usuario.setTipoUsuario(tipoUsuario);
    usuario.setFechaLogin(new Date());

    switch (tipoUsuario) {
      case VISITANTE:
        Visitante visitante = new Visitante();
        visitante.setNombre(nombre);
        visitante.setApellido(apellido);
        visitante.setFechaNacimiento(fechaNacimiento);
        visitante.setUsuario(usuario);
       
        visitanteService.crearVisitante(visitante);
        break;

      case ADMIN:
        Admin admin = new Admin();
        admin.setNombre(nombre);
        admin.setApellido(apellido);
        admin.setFechaNacimiento(fechaNacimiento);
        admin.setUsuario(usuario);
        
        adminService.crearCrearAdmin(admin);

        break;
    }

    emailService.SendEmail(usuario.getEmail(), "Registración exitosa", "Bienvenido");

    return usuario;
  }

  public Usuario buscarPorEmail(String email) {

    return usuarioRepository.findByEmail(email);
  }

  public Usuario buscarPor(Integer id) {
    Optional<Usuario> usuarioOp = usuarioRepository.findById(id);

    if (usuarioOp.isPresent()) {
      return usuarioOp.get();
    }

    return null;
  }

  public Map<String, Object> getUserClaims(Usuario usuario) {
    Map<String, Object> claims = new HashMap<>();

    claims.put("userType", usuario.getTipoUsuario());

    if (usuario.obtenerEntityId() != null)
      claims.put("entityId", usuario.obtenerEntityId());

    return claims;
  }

  public UserDetails getUserAsUserDetail(Usuario usuario) {
    UserDetails uDetails;

    uDetails = new User(usuario.getUsername(), usuario.getPassword(), getAuthorities(usuario));

    return uDetails;
  }

  // Usamos el tipo de datos SET solo para usar otro diferente a List private
  Set<? extends GrantedAuthority> getAuthorities(Usuario usuario) {

    Set<SimpleGrantedAuthority> authorities = new HashSet<>();

    TipoUsuarioEnum userType = usuario.getTipoUsuario();

    authorities.add(new SimpleGrantedAuthority("CLAIM_userType_" + userType.toString()));

    if (usuario.obtenerEntityId() != null)
      authorities.add(new SimpleGrantedAuthority("CLAIM_entityId_" + usuario.obtenerEntityId()));
    return authorities;
  }

}
