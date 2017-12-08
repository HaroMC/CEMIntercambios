package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.Antecedente;
import java.math.BigDecimal;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AntecedenteFacade extends AbstractFacade<Antecedente> {

    private static final Logger LOGGER
            = Logger.getLogger(AntecedenteFacade.class.getName());

    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AntecedenteFacade() {
        super(Antecedente.class);
    }
    
    public BigDecimal codigoAuto(){
        BigDecimal derp = em.createNamedQuery("Antecedente.codigoAnt", BigDecimal.class).getSingleResult(); //variable para obtener el código de antecedente y estamparlo en la variable jpql 
        if(derp==null){
            return BigDecimal.ONE;
        }else{
            return derp.add(BigDecimal.ONE);
        }
    }
    
}
