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
@Table(name = "CERTIFICADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certificado.findAll",
            query = "SELECT c FROM Certificado c")
    ,
    @NamedQuery(name = "Certificado.findByCodigo",
            query = "SELECT c FROM Certificado c WHERE c.codigo = :codigo")
    ,
    @NamedQuery(name = "Certificado.findByFechaSolicitud",
            query = "SELECT c FROM Certificado c WHERE c.fechaSolicitud = :fechaSolicitud")})
public class Certificado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private BigDecimal codigo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SOLICITUD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;

    @JoinColumn(name = "RUT_ALUMNO", referencedColumnName = "RUT_PERSONA")
    @ManyToOne(optional = false)
    private Alumno rutAlumno;

    public Certificado() {
    }

    public Certificado(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public Certificado(BigDecimal codigo, Date fechaSolicitud) {
        this.codigo = codigo;
        this.fechaSolicitud = fechaSolicitud;
    }

    public BigDecimal getCodigo() {
        return codigo;
    }

    public void setCodigo(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Alumno getRutAlumno() {
        return rutAlumno;
    }

    public void setRutAlumno(Alumno rutAlumno) {
        this.rutAlumno = rutAlumno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Certificado;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.Certificado[ codigo="
                + codigo + " ]";
    }

}
