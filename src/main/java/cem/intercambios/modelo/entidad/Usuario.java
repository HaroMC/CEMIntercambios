package cem.intercambios.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByRutPersona", query = "SELECT u FROM Usuario u WHERE u.rutPersona = :rutPersona")
    , @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.findByFechaRegistro", query = "SELECT u FROM Usuario u WHERE u.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Usuario.findByPerfil", query = "SELECT u FROM Usuario u WHERE u.perfil = :perfil")

    , @NamedQuery(name = "Usuario.validarIngreso", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario AND u.contrasena = :contrasena")
    , @NamedQuery(name = "Usuario.buscarCuentaPorRut", query = "SELECT u FROM Usuario u WHERE u.rutPersona = :rutPersona")
    //, @NamedQuery(name = "Usuario.codigoAutoIncremental", query = "SELECT MAX(u.codigo) + 1 FROM Usuario u")
})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RUT_PERSONA")
    private String rutPersona;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 127)
    @Column(name = "CONTRASENA")
    private String contrasena;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REGISTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PERFIL")
    private String perfil;
    
    @JoinColumn(name = "RUT_PERSONA",
            referencedColumnName = "RUT", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;

    public Usuario() {
    }

    public Usuario(String rutPersona) {
        this.rutPersona = rutPersona;
    }

    public Usuario(String rutPersona, String nombreUsuario, String contrasena,
            Date fechaRegistro, String perfil) {
        this.rutPersona = rutPersona;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.perfil = perfil;
    }

    public String getRutPersona() {
        return rutPersona;
    }

    public void setRutPersona(String rutPersona) {
        this.rutPersona = rutPersona;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutPersona != null ? rutPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.rutPersona == null && other.rutPersona != null) || (this.rutPersona != null && !this.rutPersona.equals(other.rutPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.Usuario[ rutPersona=" + rutPersona + " ]";
    }
    
}
