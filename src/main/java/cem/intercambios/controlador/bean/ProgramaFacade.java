package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.Programa;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class ProgramaFacade extends AbstractFacade<Programa> {

    private static final Logger LOGGER
            = Logger.getLogger(ProgramaFacade.class.getName());

    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProgramaFacade() {
        super(Programa.class);
    }

    public List<Programa> programasInscritosCel(String rutCel) {
        try {
            return em.createNamedQuery("Programa.programasInscritosCel")
                    .setParameter("rutCel", rutCel)
                    .setParameter("estado", 2)
                    .getResultList();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + ProgramaFacade.class.getName() + "\n"
                    + "Método: (List<Programa>) programasInscritosCel",
                    ex);
            return null;
        }
    }

    public List<Programa> findByEstado(short estado) {
        try {
            return em.createNamedQuery("Programa.findByEstado")
                    .setParameter("estado", estado)
                    .getResultList();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + ProgramaFacade.class.getName() + "\n"
                    + "Método: (List<Programa>) findByEstado",
                    ex);
            return null;
        }
    }

    public List<Programa> programasDisponiblesSinPostular(String rutCel) {
        try {
            return em.createNamedQuery(
                    "Programa.programasDisponiblesSinPostular")
                    .setParameter("rutCel", rutCel)
                    .getResultList();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + ProgramaFacade.class.getName() + "\n"
                    + "Método: List<Programa> programasDisponiblesSinPostular",
                    ex);
            return null;
        }
    }

}
