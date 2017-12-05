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
    @NamedQuery(name = "InscripcionCel.findAll", query = "SELECT i FROM InscripcionCel i")
    , @NamedQuery(name = "InscripcionCel.findByCodigo", query = "SELECT i FROM InscripcionCel i WHERE i.codigo = :codigo")
    , @NamedQuery(name = "InscripcionCel.findByFechaPostulacion", query = "SELECT i FROM InscripcionCel i WHERE i.fechaPostulacion = :fechaPostulacion")
    , @NamedQuery(name = "InscripcionCel.findByFechaInscripcion", query = "SELECT i FROM InscripcionCel i WHERE i.fechaInscripcion = :fechaInscripcion")
    , @NamedQuery(name = "InscripcionCel.findByEstado", query = "SELECT i FROM InscripcionCel i WHERE i.estado = :estado")
        
    , @NamedQuery(name = "InscripcionCel.programasInscritosCel", query = "SELECT i FROM InscripcionCel i INNER JOIN i.rutCel c INNER JOIN i.codPrograma p WHERE c.rutPersona = :rutPersona")
    , @NamedQuery(name = "InscripcionCel.codigoAutoIncremental", query = "SELECT MAX(i.codigo) + 1 FROM InscripcionCel i")
})
public class InscripcionCel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields
    // consider using these annotations to enforce field validation
    
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
    
    //@Basic(optional = false)
    //@NotNull
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
            Programa codPrograma, CentroEstudiosLocal rutCel, short estado) {
        this.codigo = codigo;
        this.fechaPostulacion = fechaPostulacion;
        this.codPrograma = codPrograma;
        this.rutCel = rutCel;
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

    public void setCodigo(BigDecimal codigo) {
        this.codigo = codigo;
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
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InscripcionCel)) {
            return false;
        }
        InscripcionCel other = (InscripcionCel) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.InscripcionCel[ codigo=" + codigo + " ]";
    }
    
}
