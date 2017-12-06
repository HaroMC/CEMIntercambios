package cem.intercambios.modelo.entidad;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "FAMILIA_ANFITRIONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FamiliaAnfitriona.findAll",
            query = "SELECT f FROM FamiliaAnfitriona f")
    ,
    @NamedQuery(name = "FamiliaAnfitriona.findByRutPersona",
            query = "SELECT f FROM FamiliaAnfitriona f WHERE f.rutPersona = :rutPersona")
    ,
    @NamedQuery(name = "FamiliaAnfitriona.findByCantidadIntegrantes",
            query = "SELECT f FROM FamiliaAnfitriona f WHERE f.cantidadIntegrantes = :cantidadIntegrantes")
    ,
    @NamedQuery(name = "FamiliaAnfitriona.findByEstado",
            query = "SELECT f FROM FamiliaAnfitriona f WHERE f.estado = :estado")
    ,
    @NamedQuery(name = "FamiliaAnfitriona.familiasPorPais",
            query = "SELECT f FROM FamiliaAnfitriona f INNER JOIN f.persona p WHERE p.pais = :pais")
})
public class FamiliaAnfitriona implements Serializable {


    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RUT_PERSONA")
    private String rutPersona;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CANTIDAD_INTEGRANTES")
    private short cantidadIntegrantes;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTADO")
    private short estado;
    
    @JoinColumn(name = "RUT_PERSONA",
            referencedColumnName = "RUT", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Persona persona;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutFamilia")
    private List<Antecedente> antecedenteList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rutFamilia")
    private List<InscripcionAlumno> inscripcionAlumnoList;

    public FamiliaAnfitriona() {
    }

    public FamiliaAnfitriona(String rutPersona) {
        this.rutPersona = rutPersona;
    }

    public FamiliaAnfitriona(String rutPersona, short cantidadIntegrantes,
            short estado) {
        this.rutPersona = rutPersona;
        this.cantidadIntegrantes = cantidadIntegrantes;
        this.estado = estado;
    }

    public String getRutPersona() {
        return rutPersona;
    }
    
    public short getCantidadIntegrantes() {
        return cantidadIntegrantes;
    }

    public void setCantidadIntegrantes(short cantidadIntegrantes) {
        this.cantidadIntegrantes = cantidadIntegrantes;
    }

    public short getEstado() {
        return estado;
    }

    public void setEstado(short estado) {
        this.estado = estado;
    }

    public Persona getPersona() {
        return persona;
    }
    
    @XmlTransient
    public List<Antecedente> getAntecedenteList() {
        return antecedenteList;
    }

    public void setAntecedenteList(List<Antecedente> antecedenteList) {
        this.antecedenteList = antecedenteList;
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
        return object instanceof FamiliaAnfitriona;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.FamiliaAnfitriona[ rutPersona="
                + rutPersona + " ]";
    }

}
