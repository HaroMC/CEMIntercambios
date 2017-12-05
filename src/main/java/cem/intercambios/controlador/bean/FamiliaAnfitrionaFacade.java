package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.FamiliaAnfitriona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class FamiliaAnfitrionaFacade extends AbstractFacade<FamiliaAnfitriona> {

    @PersistenceContext(unitName = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FamiliaAnfitrionaFacade() {
        super(FamiliaAnfitriona.class);
    }
    
    public List<FamiliaAnfitriona> familiasPorPais(String pais) {
        try {
            return em.createNamedQuery("FamiliaAnfitriona.familiasPorPais")
                    .setParameter("pais", pais)
                    .getResultList();
        }
        catch (NoResultException ex) {
            return null;
        }
    }
    
}
