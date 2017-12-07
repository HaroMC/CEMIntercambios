package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.InscripcionAlumno;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class InscripcionAlumnoFacade extends AbstractFacade<InscripcionAlumno> {

    private static final Logger LOGGER
            = Logger.getLogger(InscripcionAlumnoFacade.class.getName());

    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InscripcionAlumnoFacade() {
        super(InscripcionAlumno.class);
    }
/*
    public BigDecimal codigoAutoIncremental() {
        try {
            return em.createNamedQuery(
                    "InscripcionAlumno.codigoAutoIncremental",
                    BigDecimal.class).getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + InscripcionAlumnoFacade.class.getName() + "\n"
                    + "Método: (BigDecimal) codigoAutoIncremental",
                    ex);
            return null;
        }
    }
*/
    public List<InscripcionAlumno> programasInscritosYPostulados(
            String rutAlumno) {
        try {
            return em.createNamedQuery(
                    "InscripcionAlumno.programasInscritosYPostulados")
                    .setParameter("rutAlumno", rutAlumno)
                    .getResultList();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + InscripcionAlumnoFacade.class.getName() + "\n"
                    + "Método: (List<InscripcionAlumno>) "
                    + "programasInscritosYPostulados",
                    ex);
            return null;
        }
    }

    public List<InscripcionAlumno> verDetallesDelDestinoPorPrograma(
            String rutAlumno) {
        try {
            return em.createNamedQuery(
                    "InscripcionAlumno.verDetallesDelDestinoPorPrograma")
                    .setParameter("rutAlumno", rutAlumno)
                    .getResultList();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + InscripcionAlumnoFacade.class.getName() + "\n"
                    + "Método: (List<InscripcionAlumno>) "
                    + "verDetallesDelDestinoPorPrograma",
                    ex);
            return null;
        }
    }

}
