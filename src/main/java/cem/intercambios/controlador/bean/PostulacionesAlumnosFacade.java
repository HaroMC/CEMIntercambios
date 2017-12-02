package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.PostulacionesAlumnos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PostulacionesAlumnosFacade
        extends AbstractFacade<PostulacionesAlumnos> {

    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostulacionesAlumnosFacade() {
        super(PostulacionesAlumnos.class);
    }
    
}
