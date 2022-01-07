package ar.com.alkemy.disney.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.alkemy.disney.entities.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    Usuario findByEmail(String email);

    Usuario findByUsername(String username);
    
}
