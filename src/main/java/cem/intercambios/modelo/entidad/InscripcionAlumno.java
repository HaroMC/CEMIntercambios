package cem.intercambios.modelo.entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "InscripcionAlumno.findByRutAlumno",
            query = "SELECT i FROM InscripcionAlumno i WHERE i.inscripcionAlumnoPK.rutAlumno = :rutAlumno")
    ,
    @NamedQuery(name = "InscripcionAlumno.findByCodPrograma",
            query = "SELECT i FROM InscripcionAlumno i WHERE i.inscripcionAlumnoPK.codPrograma = :codPrograma")
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
    @NamedQuery(name = "InscripcionAlumno.programasInscritosYPostulados",
            query = "SELECT ia FROM InscripcionAlumno ia INNER JOIN ia.alumno al WHERE al.rutPersona = :rutAlumno")
    ,
    @NamedQuery(name = "InscripcionAlumno.verDetallesDelDestinoPorPrograma",
            query = "SELECT ia FROM InscripcionAlumno ia INNER JOIN ia.alumno al INNER JOIN ia.rutFamilia fa INNER JOIN ia.programa pr INNER JOIN pr.inscripcionCelList ic INNER JOIN ic.centroEstudiosLocal ce INNER JOIN ce.persona pe WHERE al.rutPersona = :rutAlumno")
    ,
    @NamedQuery(name = "InscripcionAlumno.calificacionesPorAlumno",
            query = "SELECT ial FROM InscripcionAlumno ial INNER JOIN ial.alumno alu INNER JOIN alu.persona per INNER JOIN ial.programa pro INNER JOIN pro.asignaturaList asi INNER JOIN asi.calificacionList cal WHERE ial.estado = 2 AND ial.inscripcionAlumnoPK.rutAlumno = :rutAlumno")
    ,
    @NamedQuery(name = "InscripcionAlumno.alumnosPorProgramaIniciado",
            query = "SELECT ia FROM InscripcionAlumno ia INNER JOIN ia.programa pr INNER JOIN ia.alumno al WHERE ia.estado = 2 AND pr.codigo = :codigoPrograma")
    ,
    @NamedQuery(name = "InscripcionAlumno.alumnoConsultaNotas",
            query = "SELECT ia FROM InscripcionAlumno ia INNER JOIN ia.alumno al INNER JOIN ia.programa pr WHERE ia.alumno.rutPersona = :rutAlumno")
})
public class InscripcionAlumno implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected InscripcionAlumnoPK inscripcionAlumnoPK;

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

    @JoinColumn(name = "RUT_ALUMNO",
            referencedColumnName = "RUT_PERSONA",
            insertable = false,
            updatable = false)
    @ManyToOne(optional = false)
    private Alumno alumno;

    @JoinColumn(name = "RUT_FAMILIA",
            referencedColumnName = "RUT_PERSONA")
    @ManyToOne(optional = false)
    private FamiliaAnfitriona rutFamilia;

    @JoinColumn(name = "COD_PROGRAMA",
            referencedColumnName = "CODIGO",
            insertable = false,
            updatable = false)
    @ManyToOne(optional = false)
    private Programa programa;

    public InscripcionAlumno() {
    }

    public InscripcionAlumno(InscripcionAlumnoPK inscripcionAlumnoPK) {
        this.inscripcionAlumnoPK = inscripcionAlumnoPK;
    }

    public InscripcionAlumno(String rutAlumno, String codPrograma) {
        this.inscripcionAlumnoPK
                = new InscripcionAlumnoPK(rutAlumno, codPrograma);
    }

    public InscripcionAlumno(InscripcionAlumnoPK llaveCompuesta,
            Date fechaPostulacion, Programa programa, Alumno alumno,
            FamiliaAnfitriona familia, short estado) {
        this.inscripcionAlumnoPK = llaveCompuesta;
        this.fechaPostulacion = fechaPostulacion;
        this.programa = programa;
        this.alumno = alumno;
        this.rutFamilia = familia;
        this.estado = estado;
    }

    public InscripcionAlumnoPK getInscripcionAlumnoPK() {
        return inscripcionAlumnoPK;
    }

    public void setInscripcionAlumnoPK(InscripcionAlumnoPK inscripcionAlumnoPK) {
        this.inscripcionAlumnoPK = inscripcionAlumnoPK;
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

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public FamiliaAnfitriona getRutFamilia() {
        return rutFamilia;
    }

    public void setRutFamilia(FamiliaAnfitriona rutFamilia) {
        this.rutFamilia = rutFamilia;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inscripcionAlumnoPK != null
                ? inscripcionAlumnoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof InscripcionAlumno;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.InscripcionAlumno[ "
                + "inscripcionAlumnoPK=" + inscripcionAlumnoPK + " ]";
    }

}
