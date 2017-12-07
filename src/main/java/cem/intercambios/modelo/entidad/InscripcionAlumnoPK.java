package cem.intercambios.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class InscripcionAlumnoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RUT_ALUMNO")
    private String rutAlumno;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "COD_PROGRAMA")
    private String codPrograma;

    public InscripcionAlumnoPK() {
    }

    public InscripcionAlumnoPK(String rutAlumno, String codPrograma) {
        this.rutAlumno = rutAlumno;
        this.codPrograma = codPrograma;
    }

    public String getRutAlumno() {
        return rutAlumno;
    }

    public String getCodPrograma() {
        return codPrograma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutAlumno != null ? rutAlumno.hashCode() : 0);
        hash += (codPrograma != null ? codPrograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof InscripcionAlumnoPK;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.InscripcionAlumnoPK[ rutAlumno="
                + rutAlumno + ", codPrograma=" + codPrograma + " ]";
    }
    
}
