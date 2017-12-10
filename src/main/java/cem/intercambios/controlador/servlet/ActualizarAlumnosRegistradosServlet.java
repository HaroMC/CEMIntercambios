package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.AlumnoFacade;
import cem.intercambios.controlador.bean.PersonaFacade;
import cem.intercambios.modelo.entidad.Alumno;
import cem.intercambios.controlador.cliente.ProcesoValidarAlumnos;
import cem.intercambios.controlador.cliente.ProcesoValidarAlumnos_Service;
import cem.intercambios.modelo.entidad.Persona;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

public class ActualizarAlumnosRegistradosServlet extends HttpServlet {

    private static final Logger LOG
            = Logger.getLogger(
                    ActualizarAlumnosRegistradosServlet.class.getName());

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_36124/"
            + "ServicioAlumnosMatriculados/ProcesoValidarAlumnos.wsdl")
    private ProcesoValidarAlumnos_Service servicio;

    @EJB
    private PersonaFacade pf;

    @EJB
    private AlumnoFacade af;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ProcesoValidarAlumnos port = servicio.getProcesoValidarAlumnosPort();
        List<cem.intercambios.controlador.cliente.Alumno> resultado
                = port.entregarListadoAlumnos().getAlumnos();
        if (resultado != null) {
            if (!resultado.isEmpty()) {
                if (persistirCambiosDetectados(resultado)) {
                    String mensaje = "Alumnos actualizados.";
                    LOG.log(Level.FINE, mensaje);
                    req.setAttribute("mensajeResultado", mensaje);
                }
            } else {
                LOG.log(Level.WARNING, "No hay alumnos en el servicio web.");
            }
        } else {
            LOG.log(Level.SEVERE, "Error de comunicación con el servicio web.");
        }
        resp.sendRedirect("cem_alumnos");
    }

    private boolean persistirCambiosDetectados(
            List<cem.intercambios.controlador.cliente.Alumno> resultado) {
        for (cem.intercambios.controlador.cliente.Alumno a : resultado) {
            Persona personaJpa = pf.find(a.getRutPersona());
            Alumno alumnoJpa = af.find(a.getRutPersona());
            if (personaJpa != null && alumnoJpa != null) {
                personaJpa.setCiudad(a.getCiudad());
                personaJpa.setDomicilio(a.getDomicilio());
                personaJpa.setCorreo(a.getCorreo());
                personaJpa.setPais(a.getPais());
                personaJpa.setTelefono(a.getTelefono());
                personaJpa.setNombreCompleto(a.getNombreCompleto());
                alumnoJpa.setEsMoroso(a.getEsMoroso());
                try {
                    pf.edit(personaJpa);
                    af.edit(alumnoJpa);
                } catch (Exception ex) {
                    LOG.log(Level.WARNING, "Error en la modificación en la "
                            + "base de datos.", ex);
                    return false;
                }
            }
        }
        return true;
    }

}
