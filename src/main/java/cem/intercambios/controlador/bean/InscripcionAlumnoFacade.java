package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.Alumno;
import cem.intercambios.modelo.entidad.InscripcionAlumno;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class InscripcionAlumnoFacade extends AbstractFacade<InscripcionAlumno> {

    private static final Logger LOG
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

    public List<InscripcionAlumno> programasInscritosYPostulados(
            String rutAlumno) {
        try {
            return em.createNamedQuery(
                    "InscripcionAlumno.programasInscritosYPostulados")
                    .setParameter("rutAlumno", rutAlumno)
                    .getResultList();
        } catch (NoResultException ex) {
            LOG.log(Level.WARNING, "Búsqueda sin resultados.\n"
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
            LOG.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + InscripcionAlumnoFacade.class.getName() + "\n"
                    + "Método: (List<InscripcionAlumno>) "
                    + "verDetallesDelDestinoPorPrograma",
                    ex);
            return null;
        }
    }

    public List<InscripcionAlumno> calificacionesPorAlumno(
            String rutAlumno) {
        try {
            return em.createNamedQuery(
                    "InscripcionAlumno.calificacionesPorAlumno")
                    .setParameter("rutAlumno", rutAlumno)
                    .getResultList();
        } catch (NoResultException ex) {
            LOG.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + InscripcionAlumnoFacade.class.getName() + "\n"
                    + "Método: (List<InscripcionAlumno>) "
                    + "calificacionesPorAlumno",
                    ex);
            return null;
        }
    }

    public List<InscripcionAlumno> alumnosPorProgramaIniciado(String codigoPrograma) {
        try {
            return em.createNamedQuery(
                    "InscripcionAlumno.alumnosPorProgramaIniciado",
                    InscripcionAlumno.class)
                    .setParameter("codigoPrograma", codigoPrograma)
                    .getResultList();
        } catch (NoResultException ex) {
            LOG.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + AlumnoFacade.class.getName() + "\n"
                    + "Método: (List<InscripcionAlumno>) alumnosPorProgramaIniciado",
                    ex);
            return null;
        }
    }

    public List<InscripcionAlumno> alumnoConsultaNotas(String rutAlumno) {
        try {
            return em.createNamedQuery(
                    "InscripcionAlumno.alumnoConsultaNotas",
                    InscripcionAlumno.class)
                    .setParameter("rutAlumno", rutAlumno)
                    .getResultList();
        } catch (NoResultException ex) {
            LOG.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + AlumnoFacade.class.getName() + "\n"
                    + "Método: (List<InscripcionAlumno>) alumnoConsultaNotas",
                    ex);
            return null;
        }
    }

}
