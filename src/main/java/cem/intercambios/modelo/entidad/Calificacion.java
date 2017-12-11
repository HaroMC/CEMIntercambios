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
@Table(name = "CALIFICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calificacion.findAll",
            query = "SELECT c FROM Calificacion c")
    ,
    @NamedQuery(name = "Calificacion.findByCodigo",
            query = "SELECT c FROM Calificacion c WHERE c.codigo = :codigo")
    ,
    @NamedQuery(name = "Calificacion.findByNota",
            query = "SELECT c FROM Calificacion c WHERE c.nota = :nota")
    ,
    @NamedQuery(name = "Calificacion.findByFechaCalificacion",
            query = "SELECT c FROM Calificacion c WHERE c.fechaCalificacion = :fechaCalificacion")
        ,
    @NamedQuery(name = "Calificacion.codigoAutoIncremental",
            query = "SELECT MAX(c.codigo) FROM Calificacion c")
})
public class Calificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private BigDecimal codigo;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "NOTA")
    private BigDecimal nota;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CALIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCalificacion;
    
    @JoinColumn(name = "RUT_ALUMNO", referencedColumnName = "RUT_PERSONA")
    @ManyToOne(optional = false)
    private Alumno rutAlumno;
    
    @JoinColumn(name = "COD_ASIGNATURA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Asignatura codAsignatura;

    public Calificacion() {
    }

    public Calificacion(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public Calificacion(BigDecimal codigo, BigDecimal nota,
            Date fechaCalificacion) {
        this.codigo = codigo;
        this.nota = nota;
        this.fechaCalificacion = fechaCalificacion;
    }

    public BigDecimal getCodigo() {
        return codigo;
    }
    
    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public Date getFechaCalificacion() {
        return fechaCalificacion;
    }

    public void setFechaCalificacion(Date fechaCalificacion) {
        this.fechaCalificacion = fechaCalificacion;
    }

    public Alumno getRutAlumno() {
        return rutAlumno;
    }

    public void setRutAlumno(Alumno rutAlumno) {
        this.rutAlumno = rutAlumno;
    }

    public Asignatura getCodAsignatura() {
        return codAsignatura;
    }

    public void setCodAsignatura(Asignatura codAsignatura) {
        this.codAsignatura = codAsignatura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Calificacion;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.Calificacion[ codigo=" 
               + codigo + " ]";
    }
    
}
