package cem.intercambios.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "INSCRIPCION_ALUMNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InscripcionAlumno.findAll",
            query = "SELECT i FROM InscripcionAlumno i")
    ,
    @NamedQuery(name = "InscripcionAlumno.findByCodigo",
            query = "SELECT i FROM InscripcionAlumno i WHERE i.codigo = :codigo")
    ,
    @NamedQuery(name = "InscripcionAlumno.findByFechaPostulacion",
            query = "SELECT i FROM InscripcionAlumno i WHERE i.fechaPostulacion = :fechaPostulacion")
    ,
    @NamedQuery(name = "InscripcionAlumno.findByFechaInscripcion",
            query = "SELECT i FROM InscripcionAlumno i WHERE i.fechaInscripcion = :fechaInscripcion")
    ,
    @NamedQuery(name = "InscripcionAlumno.findByEstado",
            query = "SELECT i FROM InscripcionAlumno i WHERE i.estado = :estado")
    ,
    @NamedQuery(name = "InscripcionAlumno.codigoAutoIncremental",
            query = "SELECT MAX(i.codigo) + 1 FROM InscripcionAlumno i")
    ,
    @NamedQuery(name = "InscripcionAlumno.programasInscritosYPostulados",
            query = "SELECT ia FROM InscripcionAlumno ia INNER JOIN ia.rutAlumno al WHERE al.rutPersona = :rutPersona")
    ,
    @NamedQuery(name = "InscripcionAlumno.verDetallesDelDestinoPorPrograma",
            query = "SELECT ia FROM InscripcionAlumno ia INNER JOIN ia.rutAlumno al INNER JOIN ia.rutFamilia fa INNER JOIN ia.codPrograma pr INNER JOIN pr.inscripcionCelList ic INNER JOIN ic.rutCel ce INNER JOIN ce.persona pe WHERE al.rutPersona = :rutAlumno")
})
public class InscripcionAlumno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private BigDecimal codigo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_POSTULACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPostulacion;

    @Column(name = "FECHA_INSCRIPCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInscripcion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private short estado;

    @JoinColumn(name = "RUT_ALUMNO", referencedColumnName = "RUT_PERSONA")
    @ManyToOne(optional = false)
    private Alumno rutAlumno;

    @JoinColumn(name = "RUT_FAMILIA", referencedColumnName = "RUT_PERSONA")
    @ManyToOne(optional = false)
    private FamiliaAnfitriona rutFamilia;

    @JoinColumn(name = "COD_PROGRAMA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Programa codPrograma;

    public InscripcionAlumno() {
    }

    public InscripcionAlumno(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public InscripcionAlumno(BigDecimal codigo, Date fechaPostulacion,
            Programa programa, Alumno alumno, FamiliaAnfitriona familia,
            short estado) {
        this.codigo = codigo;
        this.fechaPostulacion = fechaPostulacion;
        this.codPrograma = programa;
        this.rutAlumno = alumno;
        this.rutFamilia = familia;
        this.estado = estado;
    }

    public InscripcionAlumno(BigDecimal codigo, Date fechaPostulacion,
            Date fechaInscripcion, short estado) {
        this.codigo = codigo;
        this.fechaPostulacion = fechaPostulacion;
        this.fechaInscripcion = fechaInscripcion;
        this.estado = estado;
    }

    public BigDecimal getCodigo() {
        return codigo;
    }

    public Date getFechaPostulacion() {
        return fechaPostulacion;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaPostulacion(Date fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Alumno getRutAlumno() {
        return rutAlumno;
    }

    public void setRutAlumno(Alumno rutAlumno) {
        this.rutAlumno = rutAlumno;
    }

    public FamiliaAnfitriona getRutFamilia() {
        return rutFamilia;
    }

    public void setRutFamilia(FamiliaAnfitriona rutFamilia) {
        this.rutFamilia = rutFamilia;
    }

    public Programa getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(Programa codPrograma) {
        this.codPrograma = codPrograma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof InscripcionAlumno;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.InscripcionAlumno[ codigo="
                + codigo + " ]";
    }

}
