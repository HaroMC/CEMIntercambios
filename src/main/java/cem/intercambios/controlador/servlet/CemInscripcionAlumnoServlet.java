package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.InscripcionAlumnoFacade;
import cem.intercambios.modelo.entidad.InscripcionAlumno;
import cem.intercambios.modelo.entidad.InscripcionAlumnoPK;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CemInscripcionAlumnoServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(CemInscripcionAlumnoServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private InscripcionAlumnoFacade paf;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        sesion = req.getSession();
        String mensaje;
        List<InscripcionAlumno> inscripcionesAlumnos = paf.findAll();
        if (inscripcionesAlumnos != null) {
            sesion.setAttribute("inscripcionesAlumnos", inscripcionesAlumnos);
            mensaje = "Visualización correcta de todas las postulaciones de "
                    + "alumnos.";
            LOGGER.info(mensaje);
        } else {
            mensaje = "No se encontraron registros.";
            sesion.setAttribute("mensajeEstado", mensaje);
            LOGGER.info(mensaje);
        }
        resp.sendRedirect("inscripciones_alumnos.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String mensaje;
        String accion = ((req.getParameter("accion") == null)
                ? "" : req.getParameter("accion"));
        sesion = req.getSession();

        switch (accion) {

            case "modificar":
                InscripcionAlumno inscripcionEditar;
                String confirmarModificacion
                        = (req.getParameter("confirmar") == null
                        ? "" : req.getParameter("confirmar"));

                switch (confirmarModificacion) {

                    case "si":
                        inscripcionEditar
                                = (InscripcionAlumno) sesion.getAttribute("inscripcion");
                        try {
                            inscripcionEditar.setEstado(
                                    Short.parseShort(req.getParameter("estado")));
                        } catch (NumberFormatException ex) {

                        }
                        paf.edit(inscripcionEditar);
                        sesion.removeAttribute("inscripcion");
                        resp.sendRedirect("inscripciones_alumno");
                        break;

                    default:
                        inscripcionEditar = paf.find(
                                new InscripcionAlumnoPK(
                                        req.getParameter("rutAlumno"),
                                        req.getParameter("codigoPrograma"))
                        );
                        sesion.setAttribute("inscripcion", inscripcionEditar);
                        sesion.setAttribute("tipo", "alumno");
                        resp.sendRedirect("ver_detalles.jsp");
                }
                break;
        }
    }
}
