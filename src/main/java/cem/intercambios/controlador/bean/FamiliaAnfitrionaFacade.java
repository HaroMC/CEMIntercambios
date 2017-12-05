package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.FamiliaAnfitriona;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class FamiliaAnfitrionaFacade extends AbstractFacade<FamiliaAnfitriona> {

    private static final Logger LOGGER
            = Logger.getLogger(FamiliaAnfitrionaFacade.class.getName());

    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
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
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + FamiliaAnfitrionaFacade.class.getName() + "\n"
                    + "Método: (List<FamiliaAnfitriona>) familiasPorPais",
                    ex);
            return null;
        }
    }

}
