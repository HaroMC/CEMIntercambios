package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.Certificado;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CertificadoFacade extends AbstractFacade<Certificado> {

    private static final Logger LOGGER
            = Logger.getLogger(CertificadoFacade.class.getName());
    
    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CertificadoFacade() {
        super(Certificado.class);
    }
    
}
