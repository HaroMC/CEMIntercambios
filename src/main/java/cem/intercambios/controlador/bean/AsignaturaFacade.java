package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.Asignatura;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class AsignaturaFacade extends AbstractFacade<Asignatura> {

    private static final Logger LOGGER
            = Logger.getLogger(AsignaturaFacade.class.getName());

    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignaturaFacade() {
        super(Asignatura.class);
    }

    public BigDecimal codigoAutoIncremental() {
        BigDecimal codigo = em.createNamedQuery(
                "Asignatura.codigoAtuoIncremental",
                BigDecimal.class).getSingleResult();
        return (codigo == null
                ? BigDecimal.ONE
                : codigo.add(BigDecimal.ONE));
    }

}
