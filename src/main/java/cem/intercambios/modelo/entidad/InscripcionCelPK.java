package cem.intercambios.modelo.entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class InscripcionCelPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "RUT_CEL")
    private String rutCel;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "COD_PROGRAMA")
    private String codPrograma;

    public InscripcionCelPK() {
    }

    public InscripcionCelPK(String rutCel, String codPrograma) {
        this.rutCel = rutCel;
        this.codPrograma = codPrograma;
    }

    public String getRutCel() {
        return rutCel;
    }
    
    public String getCodPrograma() {
        return codPrograma;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rutCel != null ? rutCel.hashCode() : 0);
        hash += (codPrograma != null ? codPrograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof InscripcionCelPK;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.InscripcionCelPK[ rutCel="
                + rutCel + ", codPrograma=" + codPrograma + " ]";
    }
    
}
