package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.Alumno;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class AlumnoFacade extends AbstractFacade<Alumno> {

    private static final Logger LOG
            = Logger.getLogger(AlumnoFacade.class.getName());

    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlumnoFacade() {
        super(Alumno.class);
    }

    public List<String> obtenerRuts() {
        try {
            return em.createNamedQuery("Alumno.obtenerRuts", String.class)
                    .getResultList();
        } catch (NoResultException ex) {
            LOG.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + AlumnoFacade.class.getName() + "\n"
                    + "Método: (List<String>) obtenerRuts",
                    ex);
            return null;
        }
    }

}
