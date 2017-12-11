package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.AlumnoFacade;
import cem.intercambios.controlador.bean.AsignaturaFacade;
import cem.intercambios.controlador.bean.CalificacionFacade;
import cem.intercambios.controlador.bean.InscripcionAlumnoFacade;
import cem.intercambios.modelo.entidad.Alumno;
import cem.intercambios.modelo.entidad.InscripcionAlumno;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CemAlumnoServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(IniciarSesionServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private AlumnoFacade af;

    @EJB
    private InscripcionAlumnoFacade iaf;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        sesion = req.getSession();
        String mensaje;
        List<Alumno> listadoAlumnos = af.findAll();

        if (listadoAlumnos != null) {
            ordenarLista(listadoAlumnos);
            sesion.setAttribute("listadoAlumnos", listadoAlumnos);
            mensaje = "Visualización correcta de todos los alumnos.";
            LOGGER.info(mensaje);
        } else {
            mensaje = "No se encontraron registros.";
            sesion.setAttribute("mensajeEstado", mensaje);
            LOGGER.info(mensaje);
        }
        resp.sendRedirect("administrar_alumnos.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String mensaje, accion = req.getParameter("accion");
        sesion = req.getSession();

        switch (accion) {

            case "eliminar_alumno":
                af.remove(af.find(req.getParameter("rut")));
                mensaje = "Programa eliminado correctamente.";
                LOGGER.info(mensaje);
                sesion.setAttribute("mensajeEstado", mensaje);
                break;

            case "ver_notas":

                List<InscripcionAlumno> notasAlumnoSeleccionado
                        = iaf.calificacionesPorAlumno(req.getParameter("rut"));

                if (notasAlumnoSeleccionado != null) {
                    sesion.setAttribute("inscripcionesAl",
                            notasAlumnoSeleccionado);
                }

                break;
        }
        resp.sendRedirect("cem_alumnos");
    }

    private void ordenarLista(List<Alumno> listadoAlumnos) {
        Collections.sort(listadoAlumnos, new Comparator<Alumno>() {
            @Override
            public int compare(Alumno a1, Alumno a2) {
                return a1.getRutPersona().compareTo(a2.getRutPersona());
            }
        });
    }

}
