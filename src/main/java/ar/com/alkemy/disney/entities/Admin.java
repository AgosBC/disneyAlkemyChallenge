package ar.com.alkemy.disney.entities;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Integer adminId;

    @OneToOne(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuario;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        usuario.setAdmin(this);
    }

}
