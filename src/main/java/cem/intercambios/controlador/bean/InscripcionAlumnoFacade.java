package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.InscripcionAlumno;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
    
}
