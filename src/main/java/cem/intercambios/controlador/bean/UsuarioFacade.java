package cem.intercambios.controlador.bean;

import cem.intercambios.modelo.entidad.Usuario;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    private static final Logger LOGGER
            = Logger.getLogger(UsuarioFacade.class.getName());

    @PersistenceContext(unitName
            = "cem.intercambios_CEMIntercambios_war_1.0.0.SnapshotPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    /**
     * Método que encripta la contraseña con el algoritmo MD5 para guardarla con
     * mayor seguridad en la base de datos.
     *
     * @param contrasena La contraseña que será encriptada.
     * @return La nueva contraseña encriptada.
     * @throws NoSuchAlgorithmException
     */
    public String encriptar(String contrasena)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(contrasena.getBytes());
        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(
                    Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                            .substring(1)
            );
        }
        return sb.toString();
    }

    /**
     * Método que valida que la combinación de usuario y contraseña enviadas por
     * parámetro exista en la base de datos.
     *
     * @param nombreUsuario Nombre de la cuenta de usuario.
     * @param contrasena Contraseña de la cuenta de usuario.
     * @return Un objeto <code>Usuario</code> si es que se encuentra.
     */
    public Usuario validarIngreso(String nombreUsuario, String contrasena) {
        try {
            return em.createNamedQuery("Usuario.validarIngreso", Usuario.class)
                    .setParameter("nombreUsuario", nombreUsuario)
                    .setParameter("contrasena", contrasena)
                    .getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + UsuarioFacade.class.getName() + "\n"
                    + "Método: (Usuario) validarIngreso",
                    ex);
            return null;
        }
    }

    /**
     * Método que retorna la cuenta de usuario asociada al rut enviado por
     * parámetro.
     *
     * @param rut Identificador único de la persona dueña de la cuenta.
     * @return Un objeto <code>Usuario</code> con los datos de la cuenta.
     */
    public Usuario buscarCuentaPorRut(String rut) {
        try {
            return em.createNamedQuery(
                    "Usuario.buscarCuentaPorRut", Usuario.class)
                    .setParameter("rutPersona", rut)
                    .getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + UsuarioFacade.class.getName() + "\n"
                    + "Método: (Usuario) buscarCuentaPorRut",
                    ex);
            return null;
        }
    }

    /**
     *
     * @return El último código registrado en la base de datos más uno.
     */
    public BigDecimal codigoAutoIncremental() {
        try {
            return em.createNamedQuery("Usuario.codigoAutoIncremental",
                    BigDecimal.class).getSingleResult();
        } catch (NoResultException ex) {
            LOGGER.log(Level.WARNING, "Búsqueda sin resultados.\n"
                    + "Clase: " + UsuarioFacade.class.getName() + "\n"
                    + "Método: (BigDecimal) codigoAutoIncremental",
                    ex);
            return null;
        }
    }

}
