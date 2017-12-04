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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String mensaje;
        String nombreUsuario = req.getParameter("nombreUsuario");
        String contrasena = req.getParameter("contrasena");

        try {
            Usuario usuarioActual = uf.validarIngreso(nombreUsuario, uf.encriptar(contrasena));
            if (usuarioActual != null) {
                sesion = req.getSession(true);
                sesion.setAttribute("usuarioActual", usuarioActual);
                
                mensaje = "Bienvenido(a) " + usuarioActual.getNombreUsuario();
                
                sesion.setAttribute("mensajeBienvenida", mensaje);
                LOGGER.info("Ingreso exitoso.");
                redirecionarPerfil(resp, usuarioActual.getPerfil());
            } else {
                mensaje = "Sus credenciales no son válidas.";
                req.setAttribute("mensajeError", mensaje);
                req.getRequestDispatcher("login.jsp")
                        .forward(req, resp);
            }
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.log(Level.SEVERE, "Error en el inicio de sesión.", ex);
        }
    }

    private void redirecionarPerfil(HttpServletResponse resp, String perfil)
            throws ServletException, IOException {
        switch (perfil) {
            case "Cem":
                resp.sendRedirect("cem/cem-programas");
                break;
            case "Cel":
                resp.sendRedirect("cel/inicio.jsp");
                break;
            case "Alumno":
                resp.sendRedirect("alumno/inicio.jsp");
                break;
            case "Familia":
                resp.sendRedirect("familia/inicio.jsp");
                break;
            default:
                resp.sendRedirect("error/no-autorizado.jsp");
        }
    }

}
