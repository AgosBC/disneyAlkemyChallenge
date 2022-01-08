package ar.com.alkemy.disney.entities;


import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @NaturalId
    public String username;

    @NaturalId
    public String email;

    private String password;

    @Column(name = "fecha_login")
    private Date fechaLogin;

    @Column(name = "tipo_usuario_id")
    private Integer tipoUsuario;

    @OneToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "admin_id")
    private Admin admin;

    @OneToOne
    @JoinColumn(name = "visitante_id", referencedColumnName = "visitante_id")
    private Visitante visitante;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaLogin() {
        return fechaLogin;
    }

    public void setFechaLogin(Date fechaLogin) {
        this.fechaLogin = fechaLogin;
    }

    public TipoUsuarioEnum getTipoUsuario() {
        return TipoUsuarioEnum.parse(this.tipoUsuario);
    }

    public void setTipoUsuario(TipoUsuarioEnum tipoUsuario) {
        this.tipoUsuario = tipoUsuario.getValue();
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Visitante getVisitante() {
        return visitante;
    }

    public void setVisitante(Visitante visitante) {
        this.visitante = visitante;
    }

    public Integer obtenerEntityId() {
        switch (this.getTipoUsuario()) {
            case ADMIN:
                return this.getAdmin().getAdminId();
            case visitante:
                return this.getVisitante().getVisitanteId();
            default:
                break;
        }
        return null;
    }



    public enum TipoUsuarioEnum {
        ADMIN(1), visitante(2);

        private final Integer value;

        // NOTE: Enum constructor tiene que estar en privado
        private TipoUsuarioEnum(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public static TipoUsuarioEnum parse(Integer id) {
            TipoUsuarioEnum status = null; // Default
            for (TipoUsuarioEnum item : TipoUsuarioEnum.values()) {
                if (item.getValue().equals(id)) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }

    
    
}
