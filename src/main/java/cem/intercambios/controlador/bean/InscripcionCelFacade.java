package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.InscripcionCel;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class InscripcionCelFacade extends AbstractFacade<InscripcionCel> {

    private static final Logger LOGGER
            = Logger.getLogger(InscripcionCelFacade.class.getName());

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
        try {
            return em.createNamedQuery("InscripcionCel.programasInscritosCel")
                    .setParameter("rutPersona", rutPersona)
                    .getResultList();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + InscripcionCelFacade.class.getName() + "\n"
                    + "Método: (List<InscripcionCel>) programasInscritosCel",
                    ex);
            return null;
        }
    }
/*
    public BigDecimal codigoAutoIncremental() {
        try {
            return em.createNamedQuery("InscripcionCel.codigoAutoIncremental",
                    BigDecimal.class).getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + InscripcionCelFacade.class.getName() + "\n"
                    + "Método: (BigDecimal) codigoAutoIncremental",
                    ex);
            return null;
        }
    }
*/
    public List<InscripcionCel> findByEstado(short estado) {
        try {
            return em.createNamedQuery("InscripcionCel.findByEstado")
                    .setParameter("estado", estado)
                    .getResultList();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + InscripcionCelFacade.class.getName() + "\n"
                    + "Método: (List<InscripcionCel>) findByEstado",
                    ex);
            return null;
        }
    }

    public List<InscripcionCel> programasDisponiblesPorPaisConFamilias() {
        try {
            return em.createNamedQuery(
                    "InscripcionCel.programasDisponiblesPorPaisConFamilias")
                    .getResultList();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + InscripcionCelFacade.class.getName() + "\n"
                    + "Método: (List<InscripcionCel>) "
                    + "programasDisponiblesPorPaisConFamilias",
                    ex);
            return null;
        }
    }
}
