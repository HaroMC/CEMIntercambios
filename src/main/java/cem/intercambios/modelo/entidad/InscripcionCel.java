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
@Table(name = "INSCRIPCION_CEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InscripcionCel.findAll",
            query = "SELECT i FROM InscripcionCel i")
    ,
    @NamedQuery(name = "InscripcionCel.findByRutCel",
            query = "SELECT i FROM InscripcionCel i WHERE i.inscripcionCelPK.rutCel = :rutCel")
    ,
    @NamedQuery(name = "InscripcionCel.findByCodPrograma",
            query = "SELECT i FROM InscripcionCel i WHERE i.inscripcionCelPK.codPrograma = :codPrograma")
    ,
    @NamedQuery(name = "InscripcionCel.findByFechaPostulacion",
            query = "SELECT i FROM InscripcionCel i WHERE i.fechaPostulacion = :fechaPostulacion")
    ,
    @NamedQuery(name = "InscripcionCel.findByFechaInscripcion",
            query = "SELECT i FROM InscripcionCel i WHERE i.fechaInscripcion = :fechaInscripcion")
    ,
    @NamedQuery(name = "InscripcionCel.findByEstado",
            query = "SELECT i FROM InscripcionCel i WHERE i.estado = :estado")
    ,
    @NamedQuery(name = "InscripcionCel.programasInscritosCel",
            query = "SELECT i FROM InscripcionCel i INNER JOIN i.centroEstudiosLocal c INNER JOIN i.programa p WHERE c.rutPersona = :rutPersona")
    ,
    @NamedQuery(name = "InscripcionCel.programasDisponiblesPorPaisConFamilias",
            query = "SELECT ic1 FROM Programa pr INNER JOIN pr.inscripcionCelList ic1 INNER JOIN ic1.centroEstudiosLocal cel INNER JOIN cel.persona per1 WHERE pr.estado = 2 AND ic1.estado = 2 AND per1.pais IN ( SELECT per2.pais FROM FamiliaAnfitriona fa INNER JOIN fa.persona per2 )")
})
public class InscripcionCel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected InscripcionCelPK inscripcionCelPK;
    
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

    @JoinColumn(name = "RUT_CEL",
            referencedColumnName = "RUT_PERSONA",
            insertable = false,
            updatable = false)
    @ManyToOne(optional = false)
    private CentroEstudiosLocal centroEstudiosLocal;

    @JoinColumn(name = "COD_PROGRAMA",
            referencedColumnName = "CODIGO",
            insertable = false,
            updatable = false)
    @ManyToOne(optional = false)
    private Programa programa;

    public InscripcionCel() {
    }
    
    public InscripcionCel(InscripcionCelPK inscripcionCelPK) {
        this.inscripcionCelPK = inscripcionCelPK;
    }

    public InscripcionCel(InscripcionCelPK llaveCompuesta,
            Date fechaPostulacion, Programa programa, CentroEstudiosLocal cel,
            short estado) {
        this.inscripcionCelPK = llaveCompuesta;
        this.fechaPostulacion = fechaPostulacion;
        this.programa = programa;
        this.centroEstudiosLocal = cel;
        this.estado = estado;
    }

    public InscripcionCelPK getInscripcionCelPK() {
        return inscripcionCelPK;
    }

    public void setInscripcionCelPK(InscripcionCelPK inscripcionCelPK) {
        this.inscripcionCelPK = inscripcionCelPK;
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

    public CentroEstudiosLocal getCentroEstudiosLocal() {
        return centroEstudiosLocal;
    }

    public void setCentroEstudiosLocal(CentroEstudiosLocal rutCel) {
        this.centroEstudiosLocal = rutCel;
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
        hash += (inscripcionCelPK != null ? inscripcionCelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof InscripcionAlumno;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.InscripcionCel[ "
                + "inscripcionCelPK=" + inscripcionCelPK + " ]";
    }
    
}
