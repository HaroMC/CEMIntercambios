package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.cliente.Alumno;
import cem.intercambios.controlador.cliente.ProcesoValidarAlumnos;
import cem.intercambios.controlador.cliente.ProcesoValidarAlumnos_Service;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

@WebServlet(name = "WebServiceConsumerServlet",
        urlPatterns = {"/WebServiceConsumerServlet"})
public class WebServiceConsumerServlet extends HttpServlet {

    private static final Logger LOG
            = Logger.getLogger(WebServiceConsumerServlet.class.getName());

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_36124/"
            + "ServicioAlumnosMatriculados/ProcesoValidarAlumnos.wsdl")
    private ProcesoValidarAlumnos_Service service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String rut = req.getParameter("rut");
        try {
            ProcesoValidarAlumnos port = service.getProcesoValidarAlumnosPort();
            Alumno result = port.verificarRutAlumno(rut);
            if (result != null) {
                req.setAttribute("mensajeEstado", "El rut ingresado es "
                        + "válido.");
                req.setAttribute("alumnoEncontrado", result);
            } else {
                req.setAttribute("mensajeEstado", "El rut ingresado no "
                        + "pertenece a un alumno matriculado en nuestra "
                        + "institución.");
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Mala respuesta del servicio web.", ex);
        }
        req.getRequestDispatcher("prueba-consumo-web-service.jsp")
                .forward(req, resp);
    }

}
