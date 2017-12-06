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
@Table(name = "INSCRIPCION_CEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InscripcionCel.findAll",
            query = "SELECT i FROM InscripcionCel i")
    ,
    @NamedQuery(name = "InscripcionCel.findByCodigo",
            query = "SELECT i FROM InscripcionCel i WHERE i.codigo = :codigo")
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
            query = "SELECT i FROM InscripcionCel i INNER JOIN i.rutCel c INNER JOIN i.codPrograma p WHERE c.rutPersona = :rutPersona")
    ,
    @NamedQuery(name = "InscripcionCel.codigoAutoIncremental",
            query = "SELECT MAX(i.codigo) + 1 FROM InscripcionCel i")
    ,
    @NamedQuery(name = "InscripcionCel.programasDisponiblesPorPaisConFamilias",
            query = "SELECT ic1 FROM Programa pr INNER JOIN pr.inscripcionCelList ic1 INNER JOIN ic1.rutCel cel INNER JOIN cel.persona per1 WHERE pr.estado = 2 AND ic1.estado = 2 AND per1.pais IN ( SELECT per2.pais FROM FamiliaAnfitriona fa INNER JOIN fa.persona per2 )")
})
public class InscripcionCel implements Serializable {

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

    @JoinColumn(name = "RUT_CEL", referencedColumnName = "RUT_PERSONA")
    @ManyToOne(optional = false)
    private CentroEstudiosLocal rutCel;

    @JoinColumn(name = "COD_PROGRAMA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Programa codPrograma;

    public InscripcionCel() {
    }

    public InscripcionCel(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public InscripcionCel(BigDecimal codigo, Date fechaPostulacion,
            Programa programa, CentroEstudiosLocal cel, short estado) {
        this.codigo = codigo;
        this.fechaPostulacion = fechaPostulacion;
        this.codPrograma = programa;
        this.rutCel = cel;
        this.estado = estado;
    }

    public InscripcionCel(BigDecimal codigo, Date fechaPostulacion,
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

    public CentroEstudiosLocal getRutCel() {
        return rutCel;
    }

    public void setRutCel(CentroEstudiosLocal rutCel) {
        this.rutCel = rutCel;
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
        return "cem.intercambios.modelo.entidad.InscripcionCel[ codigo="
                + codigo + " ]";
    }

}
