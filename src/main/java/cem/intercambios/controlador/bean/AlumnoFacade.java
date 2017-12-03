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
public class AlumnoFacade
        extends AbstractFacade<Alumno> {

    private static final Logger LOGGER
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

    @Override
    public List<Alumno> findAll() {
        return super.findAll();
    }

    public Alumno buscarNotas(String estadoPostulacion, String rutAlumno) {
        try {
            return em.createNamedQuery("Alumno.buscarNotas", Alumno.class)
                    .setParameter("estadoPostulacion", estadoPostulacion)
                    .setParameter("rutAlumno", rutAlumno)
                    .getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.", ex);
            return null;
        }
    }

}
