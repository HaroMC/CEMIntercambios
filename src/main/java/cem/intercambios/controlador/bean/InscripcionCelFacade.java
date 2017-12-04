package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.InscripcionCel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class InscripcionCelFacade extends AbstractFacade<InscripcionCel> {

    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InscripcionCelFacade() {
        super(InscripcionCel.class);
    }
    
    public List<InscripcionCel> programasInscritosCel(String rutPersona) {
        return em.createNamedQuery("Programa.programasInscritosCel")
                .setParameter("rutPersona", rutPersona)
                .getResultList();
    }
    
}
