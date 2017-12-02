package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.Alumno;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AlumnoFacade
        extends AbstractFacade<Alumno> {

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

}
