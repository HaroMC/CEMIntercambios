package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.AlumnoFacade;
import cem.intercambios.modelo.entidad.Alumno;
import java.io.IOException;
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

/**
 *
 * @author HaroMC
 * @version 1.0.0
 * @since 2017-12-01
 */
public class CemAlumnoServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(IniciarSesionServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private AlumnoFacade af;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        sesion = request.getSession();
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
        response.sendRedirect("administrar-alumnos.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String mensaje, accion = request.getParameter("accion");
        sesion = request.getSession();
                
        switch (accion) {

            case "eliminar":
                af.remove(af.find(request.getParameter("rut")));
                mensaje = "Programa eliminado correctamente.";
                LOGGER.info(mensaje);
                sesion.setAttribute("mensajeEstado", mensaje);
                break;
                
            case "modificar":
        }
        response.sendRedirect("cem-alumnos");
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
