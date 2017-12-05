package cem.intercambios.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "PROGRAMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programa.findAll",
            query = "SELECT p FROM Programa p")
    ,
    @NamedQuery(name = "Programa.findByCodigo",
            query = "SELECT p FROM Programa p WHERE p.codigo = :codigo")
    ,
    @NamedQuery(name = "Programa.findByNombrePrograma",
            query = "SELECT p FROM Programa p WHERE p.nombrePrograma = :nombrePrograma")
    ,
    @NamedQuery(name = "Programa.findByFechaInicio",
            query = "SELECT p FROM Programa p WHERE p.fechaInicio = :fechaInicio")
    ,
    @NamedQuery(name = "Programa.findByFechaTermino",
            query = "SELECT p FROM Programa p WHERE p.fechaTermino = :fechaTermino")
    ,
    @NamedQuery(name = "Programa.findByCupos",
            query = "SELECT p FROM Programa p WHERE p.cupos = :cupos")
    ,
    @NamedQuery(name = "Programa.findByValor",
            query = "SELECT p FROM Programa p WHERE p.valor = :valor")
    ,
    @NamedQuery(name = "Programa.findByEstado",
            query = "SELECT p FROM Programa p WHERE p.estado = :estado")
    ,
    @NamedQuery(name = "Programa.programasInscritosCel",
            query = "SELECT p FROM InscripcionCel i INNER JOIN i.rutCel c INNER JOIN i.codPrograma p WHERE c.rutPersona = :rutPersona AND i.estado = :estado")
    ,
    @NamedQuery(name = "Programa.programasDisponiblesSinPostular",
            query = "SELECT p FROM Programa p LEFT JOIN p.inscripcionCelList ic1 WHERE p.estado = 1 AND p.codigo NOT IN ( SELECT ic2.codPrograma.codigo FROM InscripcionCel ic2 WHERE ic2.rutCel.rutPersona = :rutCel )")
    ,
    @NamedQuery(name = "Programa.ultimoCodigo",
            query = "SELECT MAX(p.codigo) FROM Programa p")
})
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CODIGO")
    private String codigo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOMBRE_PROGRAMA")
    private String nombrePrograma;

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_TERMINO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTermino;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CUPOS")
    private long cupos;

    @Basic(optional = false)
    @NotNull
    @Column(name = "VALOR")
    private long valor;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private short estado;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPrograma")
    private List<Asignatura> asignaturaList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPrograma")
    private List<InscripcionAlumno> inscripcionAlumnoList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codPrograma")
    private List<InscripcionCel> inscripcionCelList;

    public Programa() {
    }

    public Programa(String codigo) {
        this.codigo = codigo;
    }

    public Programa(String codigo, String nombrePrograma, long valor,
            long cupos, short estado) {
        this.codigo = codigo;
        this.nombrePrograma = nombrePrograma;
        this.valor = valor;
        this.cupos = cupos;
        this.estado = estado;
    }

    public Programa(String codigo, String nombrePrograma, Date fechaInicio,
            Date fechaTermino, long cupos, short estado) {

        this.codigo = codigo;
        this.nombrePrograma = nombrePrograma;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.cupos = cupos;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public long getCupos() {
        return cupos;
    }

    public void setCupos(long cupos) {
        this.cupos = cupos;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    @XmlTransient
    public List<InscripcionAlumno> getInscripcionAlumnoList() {
        return inscripcionAlumnoList;
    }

    public void setInscripcionAlumnoList(
            List<InscripcionAlumno> inscripcionAlumnoList) {
        this.inscripcionAlumnoList = inscripcionAlumnoList;
    }

    @XmlTransient
    public List<InscripcionCel> getInscripcionCelList() {
        return inscripcionCelList;
    }

    public void setInscripcionCelList(List<InscripcionCel> inscripcionCelList) {
        this.inscripcionCelList = inscripcionCelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields
        // are not set
        /*if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.codigo == null && other.codigo != null) ||
                (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;*/
        return object instanceof Programa;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.Programa[ codigo="
                + codigo + " ]";
    }

}
