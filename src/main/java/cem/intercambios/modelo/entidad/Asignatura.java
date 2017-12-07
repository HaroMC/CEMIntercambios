package cem.intercambios.modelo.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "ASIGNATURA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll",
            query = "SELECT a FROM Asignatura a")
    ,
    @NamedQuery(name = "Asignatura.findByCodigo",
            query = "SELECT a FROM Asignatura a WHERE a.codigo = :codigo")
    ,
    @NamedQuery(name = "Asignatura.findByNombreAsignatura",
            query = "SELECT a FROM Asignatura a WHERE a.nombreAsignatura = :nombreAsignatura")
    ,
    @NamedQuery(name = "Asignatura.findByDescripcion",
            query = "SELECT a FROM Asignatura a WHERE a.descripcion = :descripcion")
    ,
    @NamedQuery(name = "Asignatura.codigoAtuoIncremental",
            query = "SELECT MAX(a.codigo) FROM Asignatura a")
})
public class Asignatura implements Serializable {

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
    @Column(name = "NOMBRE_ASIGNATURA")
    private String nombreAsignatura;

    @Size(max = 250)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codAsignatura")
    private List<Calificacion> calificacionList;

    @JoinColumn(name = "RUT_DOCENTE", referencedColumnName = "RUT_PERSONA")
    @ManyToOne
    private Docente rutDocente;

    @JoinColumn(name = "COD_PROGRAMA", referencedColumnName = "CODIGO")
    @ManyToOne(optional = false)
    private Programa codPrograma;

    public Asignatura() {
    }

    public Asignatura(String codigo) {
        this.codigo = codigo;
    }

    public Asignatura(String codigo, String nombreAsignatura) {
        this.codigo = codigo;
        this.nombreAsignatura = nombreAsignatura;
    }
    
    public Asignatura(String codigo, String nombreAsignatura,
            String descripcion, Programa programa) {
        this.codigo = codigo;
        this.nombreAsignatura = nombreAsignatura;
        this.descripcion = descripcion;
        this.codPrograma = programa;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Calificacion> getCalificacionList() {
        return calificacionList;
    }

    public void setCalificacionList(List<Calificacion> calificacionList) {
        this.calificacionList = calificacionList;
    }

    public Docente getRutDocente() {
        return rutDocente;
    }

    public void setRutDocente(Docente rutDocente) {
        this.rutDocente = rutDocente;
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
        return object instanceof Asignatura;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.Asignatura[ codigo="
                + codigo + " ]";
    }

}
