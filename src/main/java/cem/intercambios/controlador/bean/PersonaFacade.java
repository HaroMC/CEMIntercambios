package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.Persona;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    private static final Logger LOGGER
            = Logger.getLogger(PersonaFacade.class.getName());
    
    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }
    
}
