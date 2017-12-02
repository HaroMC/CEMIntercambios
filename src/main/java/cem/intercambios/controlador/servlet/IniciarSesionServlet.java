package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.UsuarioFacade;
import cem.intercambios.modelo.entidad.Usuario;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HaroMC
 * @version 1.0.0
 * @since 2017-12-01
 */
public class IniciarSesionServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(IniciarSesionServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private UsuarioFacade uf;

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String mensaje;
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasena = request.getParameter("contrasena");

        try {
            Usuario usuarioActual = uf.validarIngreso(
                    nombreUsuario, uf.encriptar(contrasena)
            );
            if (usuarioActual != null) {
                sesion = request.getSession(true);
                sesion.setAttribute("usuarioActual", usuarioActual);
                mensaje = "Bienvenido(a) " + usuarioActual.getNombre();
                sesion.setAttribute("mensajeBienvenida", mensaje);
                LOGGER.info("Ingreso exitoso.");
                redirecionarPerfil(response, usuarioActual.getPerfil());
            } else {
                mensaje = "Sus credenciales no son válidas.";
                request.setAttribute("mensajeError", mensaje);
                request.getRequestDispatcher("login.jsp")
                        .forward(request, response);
            }
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.log(Level.SEVERE, "Error en el inicio de sesión.", ex);
        }
    }

    /**
     *
     * @param response
     * @param perfil
     * @throws ServletException
     * @throws IOException
     */
    private void redirecionarPerfil(HttpServletResponse response, String perfil)
            throws ServletException, IOException {
        switch (perfil) {
            case "Cem":
                response.sendRedirect("cem/cem-programas");
                break;
            case "Cel":
                response.sendRedirect("cel/inicio.jsp");
                break;
            case "Alumno":
                response.sendRedirect("alumno/inicio.jsp");
                break;
            case "Familia":
                response.sendRedirect("familia/inicio.jsp");
                break;
            default:
                response.sendRedirect("error/no-autorizado.jsp");
        }
    }

}
