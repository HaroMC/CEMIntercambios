package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.Antecedente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AntecedenteFacade extends AbstractFacade<Antecedente> {

    @PersistenceContext(unitName = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AntecedenteFacade() {
        super(Antecedente.class);
    }
    
}
