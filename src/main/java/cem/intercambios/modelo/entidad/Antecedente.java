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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "ANTECEDENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Antecedente.codigoAnt", 
            query = "SELECT MAX(a.codigo) FROM Antecedente a")
    ,
    @NamedQuery(name = "Antecedente.findAll",
            query = "SELECT a FROM Antecedente a")
    ,
    @NamedQuery(name = "Antecedente.findByCodigo",
            query = "SELECT a FROM Antecedente a WHERE a.codigo = :codigo")
    ,
    @NamedQuery(name = "Antecedente.findByDescripcion",
            query = "SELECT a FROM Antecedente a WHERE a.descripcion = :descripcion")
    ,
    @NamedQuery(name = "Antecedente.findByFechaCaducidad",
            query = "SELECT a FROM Antecedente a WHERE a.fechaCaducidad = :fechaCaducidad")
    ,
    @NamedQuery(name = "Antecedente.findByRutaDocumento",
            query = "SELECT a FROM Antecedente a WHERE a.rutaDocumento = :rutaDocumento")
})
public class Antecedente implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO")
    private BigDecimal codigo;

    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_CADUCIDAD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaducidad;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 400)
    @Column(name = "RUTA_DOCUMENTO")
    private String rutaDocumento;

    @JoinColumn(name = "RUT_FAMILIA", referencedColumnName = "RUT_PERSONA")
    @ManyToOne(optional = false)
    private FamiliaAnfitriona rutFamilia;

    public Antecedente() {
    }

    public Antecedente(BigDecimal codigo) {
        this.codigo = codigo;
    }

    public Antecedente(BigDecimal codigo, Date fechaCaducidad) {
        this.codigo = codigo;
        this.fechaCaducidad = fechaCaducidad;
    }

    public Antecedente(BigDecimal codigo, Date fechaCaducidad, String nombreArchivo, String tipoDocumento, String rutaDocumento, FamiliaAnfitriona rutFamilia) {
        this.codigo = codigo;
        this.fechaCaducidad = fechaCaducidad;
        this.nombreArchivo = nombreArchivo;
        this.tipoDocumento = tipoDocumento;
        this.rutaDocumento = rutaDocumento;
        this.rutFamilia = rutFamilia;
    }
    
    

    public BigDecimal getCodigo() {
        return codigo;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getRutaDocumento() {
        return rutaDocumento;
    }

    public void setRutaDocumento(String rutaDocumento) {
        this.rutaDocumento = rutaDocumento;
    }

    public FamiliaAnfitriona getRutFamilia() {
        return rutFamilia;
    }

    public void setRutFamilia(FamiliaAnfitriona rutFamilia) {
        this.rutFamilia = rutFamilia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Antecedente;
    }

    @Override
    public String toString() {
        return "cem.intercambios.modelo.entidad.Antecedente[ codigo="
                + codigo + " ]";
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
