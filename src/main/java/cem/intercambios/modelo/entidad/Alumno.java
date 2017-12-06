package cem.intercambios.modelo.entidad;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "ALUMNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alumno.findAll",
            query = "SELECT a FROM Alumno a")
    ,
    @NamedQuery(name = "Alumno.findByRutPersona",
            query = "SELECT a FROM Alumno a WHERE a.rutPersona = :rutPersona")
    ,
    @NamedQuery(name = "Alumno.findByNumeroMatricula",
            query = "SELECT a FROM Alumno a WHERE a.numeroMatricula = :numeroMatricula")
    ,
    @NamedQuery(name = "Alumno.findByFechaMatricula",
            query = "SELECT a FROM Alumno a WHERE a.fechaMatricula = :fechaMatricula")
    ,
    @NamedQuery(name = "Alumno.findByNombreCarrera",
            query = "SELECT a FROM Alumno a WHERE a.nombreCarrera = :nombreCarrera")
    ,
    @NamedQuery(name = "Alumno.findByEsMoroso",
            query = "SELECT a FROM Alumno a WHERE a.esMoroso = :esMoroso")
})
public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RUT_PERSONA")
    private String rutPersona;

    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO_MATRICULA")
    private BigInteger numeroMatricula;

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_MATRICULA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMatricula;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE_CARRERA")
    private String nombreCarrera;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ES_MOROSO")
    private short esMoroso;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutAlumno")
    private List<Calificacion> calificacionList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutAlumno")
    private List<Certificado> certificadoList;

    @JoinColumn(name = "RUT_PERSONA", referencedColumnName = "RUT",
            insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutAlumno")
    private List<InscripcionAlumno> inscripcionAlumnoList;

    public Alumno() {
    }

    public Alumno(String rutPersona) {
        this.rutPersona = rutPersona;
    }

    public Alumno(String rutPersona, BigInteger numeroMatricula,
            Date fechaMatricula, String nombreCarrera, short esMoroso) {
        this.rutPersona = rutPersona;
        this.numeroMatricula = numeroMatricula;
        this.fechaMatricula = fechaMatricula;
        this.nombreCarrera = nombreCarrera;
        this.esMoroso = esMoroso;
    }

    public String getRutPersona() {
        return rutPersona;
    }

    public BigInteger getNumeroMatricula() {
        return numeroMatricula;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public short getEsMoroso() {
        return esMoroso;
    }

    public void setEsMoroso(short esMoroso) {
        this.esMoroso = esMoroso;
    }

    @XmlTransient
    public List<Calificacion> getCalificacionList() {
        return calificacionList;
    }

    public void setCalificacionList(List<Calificacion> calificacionList) {
        this.calificacionList = calificacionList;
    }

    @XmlTransient
    public List<Certificado> getCertificadoList() {
        return certificadoList;
    }

    public void setCertificadoList(List<Certificado> certificadoList) {
        this.certificadoList = certificadoList;
    }

    public Persona getPersona() {
        return persona;
    }

    @XmlTransient
    public List<InscripcionAlumno> getInscripcionAlumnoList() {
        return inscripcionAlumnoList;
    }

    public void setInscripcionAlumnoList(
            List<InscripcionAlumno> inscripcionAlumnoList) {
        this.inscripcionAlumnoList = inscripcionAlumnoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutPersona != null ? rutPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields
        // are not set
        /*if (!(object instanceof Alumno)) {
            return false;
        }
        Alumno other = (Alumno) object;
        if ((this.rutPersona == null && other.rutPersona != null) ||
                (this.rutPersona != null &&
                !this.rutPersona.equals(other.rutPersona))) {
            return false;
        }
        return true;*/
        return object instanceof Alumno;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.Alumno[ rutPersona="
                + rutPersona + " ]";
    }

}
