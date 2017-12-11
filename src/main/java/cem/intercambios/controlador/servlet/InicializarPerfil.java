package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.InscripcionAlumnoFacade;
import cem.intercambios.modelo.entidad.InscripcionAlumno;
import cem.intercambios.modelo.entidad.InscripcionAlumnoPK;
import cem.intercambios.modelo.entidad.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InicializarPerfil extends HttpServlet {

    private HttpSession sesion;

    private static final Logger LOGGER
            = Logger.getLogger(InicializarPerfil.class.getName());

    @EJB
    private InscripcionAlumnoFacade iaf;

    // <editor-fold defaultstate="collapsed" desc=" GET ">
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        obtenerSesionActiva(req, resp);
        cargarPerfil(resp);
    }
    // </editor-fold>

    private void cargarPerfil(HttpServletResponse resp)
            throws ServletException, IOException {

        Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
        /*switch (usuarioActual.getPerfil()) {
            case "Cem":
                resp.sendRedirect("cem/cem_programas");
                break;
            case "Cel":
                resp.sendRedirect("cel/inicio.jsp");
                break;
            case "Alumno":
                break;
            case "Familia":
                resp.sendRedirect("familia/inicio.jsp");
                break;
            default:
                resp.sendRedirect("error/no_autorizado.jsp");
        }*/

        List<InscripcionAlumno> inscripcionesAlumno
                = iaf.verDetallesDelDestinoPorPrograma(
                        usuarioActual.getRutPersona());

        if (inscripcionesAlumno != null) {
            sesion.setAttribute("inscripcionesAlumno",
                    inscripcionesAlumno);
            resp.sendRedirect("alumno/inicio.jsp");

        }

    }

    private void obtenerSesionActiva(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {

        sesion = req.getSession();
        if (sesion.getAttribute("usuarioActual") == null) {
            resp.sendRedirect("../error/no_autorizado.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession sesion = req.getSession();
        String accion = (req.getParameter("accion") == null ? "" : req.getParameter("accion"));

        switch (accion) {

            case "consultar":

                String rutAlumno = req.getParameter("rutAlumno");
                String codPrograma = req.getParameter("codPrograma");
                InscripcionAlumnoPK llave = new InscripcionAlumnoPK(rutAlumno, codPrograma);

                try {
                    InscripcionAlumno seleccionado = iaf.find(llave);
                    sesion.setAttribute("inscPrograAsigCaliAlum", seleccionado);
                    resp.sendRedirect("alumno/inicio.jsp");

                } catch (Exception ex) {

                }

                break;

            default:

        }

    }

}
