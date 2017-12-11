package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.Calificacion;
import java.math.BigDecimal;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class CalificacionFacade extends AbstractFacade<Calificacion> {

    private static final Logger LOGGER
            = Logger.getLogger(CalificacionFacade.class.getName());

    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalificacionFacade() {
        super(Calificacion.class);
    }

    public BigDecimal codigoAutoIncremental() {
        BigDecimal codigo = em.createNamedQuery(
                "Calificacion.codigoAutoIncremental", BigDecimal.class)
                .getSingleResult();
        return (codigo == null
                ? BigDecimal.ONE
                : codigo.add(BigDecimal.ONE));
    }

}
