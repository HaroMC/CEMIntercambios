package cem.intercambios.modelo.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "DOCENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docente.findAll",
            query = "SELECT d FROM Docente d")
    ,
    @NamedQuery(name = "Docente.findByRutPersona",
            query = "SELECT d FROM Docente d WHERE d.rutPersona = :rutPersona")
    ,
    @NamedQuery(name = "Docente.findByObservaciones",
            query = "SELECT d FROM Docente d WHERE d.observaciones = :observaciones")})
public class Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RUT_PERSONA")
    private String rutPersona;
    
    @Size(max = 255)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    
    @OneToMany(mappedBy = "rutDocente")
    private List<Asignatura> asignaturaList;
    
    @JoinColumn(name = "RUT_PERSONA", referencedColumnName = "RUT",
            insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;
    

    public Docente() {
    }

    public Docente(String rutPersona) {
        this.rutPersona = rutPersona;
    }

    public String getRutPersona() {
        return rutPersona;
    }
    
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    public Persona getPersona() {
        return persona;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutPersona != null ? rutPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Docente;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.Docente[ rutPersona="
                + rutPersona + " ]";
    }
    
}
