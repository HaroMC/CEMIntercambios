package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.AlumnoFacade;
import cem.intercambios.controlador.bean.CalificacionFacade;
import cem.intercambios.controlador.bean.InscripcionAlumnoFacade;
import cem.intercambios.controlador.bean.ProgramaFacade;
import cem.intercambios.modelo.entidad.InscripcionAlumno;
import cem.intercambios.modelo.entidad.Programa;
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

public class CelProgramaServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(CelProgramaServlet.class.getName());

    @EJB
    private AlumnoFacade af;

    @EJB
    private CalificacionFacade calf;

    @EJB
    private InscripcionAlumnoFacade iaf;

    @EJB
    private ProgramaFacade pf;

    private HttpSession sesion;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = (req.getParameter("accion") == null
                ? "" : req.getParameter("accion"));

        sesion = req.getSession();
        Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");

        switch (accion) {
            case "agregar_notas":
                String confirmar = req.getParameter("confirmar");
                String rut = req.getParameter("rut");

                if (confirmar == null) {
                    resp.sendRedirect("agregar_nota.jsp");
                } else {
                    if (confirmar.equalsIgnoreCase("si")) {

                        try {
                            //calf.create("");
                        } catch (Exception ex) {
                            resp.sendRedirect("cel_programas");
                        }
                        resp.sendRedirect("cel_programas");
                    }
                }
                break;

            default:
                List<Programa> programasInscritos = pf.programasInscritosCel(
                        usuarioActual.getRutPersona());
                sesion.setAttribute("programasInscritos", programasInscritos);
                resp.sendRedirect("calificaciones.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        sesion = req.getSession();
        Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");

        String accion = (req.getParameter("accion") == null
                ? "" : req.getParameter("accion"));

        switch (accion) {
            case "ver_notas_alumno":
                String codigoPrograma = req.getParameter("codigoPrograma");
                List<InscripcionAlumno> alumnosDelPrograma
                        = iaf.alumnosPorProgramaIniciado(codigoPrograma);
                if (alumnosDelPrograma != null) {
                    if (!alumnosDelPrograma.isEmpty()) {
                        sesion.setAttribute("alumnosDelPrograma",
                                alumnosDelPrograma);
                    }
                }
                resp.sendRedirect("cel_programas");
                break;
        }
    }

}
