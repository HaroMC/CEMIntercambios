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
@Table(name = "CENTRO_ESTUDIOS_LOCAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CentroEstudiosLocal.findAll",
            query = "SELECT c FROM CentroEstudiosLocal c")
    ,
    @NamedQuery(name = "CentroEstudiosLocal.findByRutPersona",
            query = "SELECT c FROM CentroEstudiosLocal c WHERE c.rutPersona = :rutPersona")
    ,
    @NamedQuery(name = "CentroEstudiosLocal.findByEstaAcreditado",
            query = "SELECT c FROM CentroEstudiosLocal c WHERE c.estaAcreditado = :estaAcreditado")})
public class CentroEstudiosLocal implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RUT_PERSONA")
    private String rutPersona;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ESTA_ACREDITADO")
    private short estaAcreditado;
    
    @JoinColumn(name = "RUT_PERSONA",
            referencedColumnName = "RUT",
            insertable = false,
            updatable = false)
    @OneToOne(optional = false)
    private Persona persona;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "centroEstudiosLocal")
    private List<InscripcionCel> inscripcionCelList;

    public CentroEstudiosLocal() {
    }

    public CentroEstudiosLocal(String rutPersona) {
        this.rutPersona = rutPersona;
    }

    public CentroEstudiosLocal(String rutPersona, short estaAcreditado) {
        this.rutPersona = rutPersona;
        this.estaAcreditado = estaAcreditado;
    }

    public String getRutPersona() {
        return rutPersona;
    }

    public short getEstaAcreditado() {
        return estaAcreditado;
    }

    public void setEstaAcreditado(short estaAcreditado) {
        this.estaAcreditado = estaAcreditado;
    }

    public Persona getPersona() {
        return persona;
    }

    @XmlTransient
    public List<InscripcionCel> getInscripcionCelList() {
        return inscripcionCelList;
    }

    public void setInscripcionCelList(List<InscripcionCel> inscripcionCelList) {
        this.inscripcionCelList = inscripcionCelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutPersona != null ? rutPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof CentroEstudiosLocal;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.CentroEstudiosLocal[ "
                + "rutPersona=" + rutPersona + " ]";
    }
    
}
