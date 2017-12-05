package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.InscripcionAlumnoFacade;
import cem.intercambios.modelo.entidad.InscripcionAlumno;
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
        List<InscripcionAlumno> inscrpcionesAlumnos = paf.findAll();
        if (inscrpcionesAlumnos != null) {
            sesion.setAttribute("inscrpcionesAlumnos", inscrpcionesAlumnos);
            mensaje = "Visualización correcta de todas las postulaciones de "
                    + "alumnos.";
            LOGGER.info(mensaje);
        } else {
            mensaje = "No se encontraron registros.";
            sesion.setAttribute("mensajeEstado", mensaje);
            LOGGER.info(mensaje);
        }
        resp.sendRedirect("inscripciones-alumnos.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

}
