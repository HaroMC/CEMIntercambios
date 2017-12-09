package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.PersonaFacade;
import cem.intercambios.controlador.cliente.ProcesoValidarAlumnos;
import cem.intercambios.controlador.cliente.ProcesoValidarAlumnos_Service;
import cem.intercambios.modelo.entidad.FamiliaAnfitriona;
import cem.intercambios.modelo.entidad.Persona;
import cem.intercambios.modelo.entidad.Usuario;
import cem.intercambios.modelo.utilidades.CemUtiles;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

public class RegistrarCuentaServlet extends HttpServlet {

    private static final Logger LOG
            = Logger.getLogger(RegistrarCuentaServlet.class.getName());

    private final CemUtiles cu = new CemUtiles();

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_36124/"
            + "ServicioAlumnosMatriculados/ProcesoValidarAlumnos.wsdl")
    private ProcesoValidarAlumnos_Service servicio;

    @EJB
    private PersonaFacade pf;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.sendRedirect("alumno.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String mensaje;
        String accion = verificarAccion(req);
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        switch (accion) {
            case "registrar_familia":

                try {
                    String rut = req.getParameter("rut");
                    if (pf.find(rut) == null) {
                        Persona nuevoRegistro = new Persona(
                                rut,
                                req.getParameter("nombreJefeFamilia") + " "
                                + req.getParameter("apellidoJefeFamilia"),
                                formatoFecha.parse(req
                                        .getParameter("fechaNacimiento")),
                                req.getParameter("domicilio"),
                                req.getParameter("ciudad"),
                                req.getParameter("pais"),
                                req.getParameter("correo"),
                                req.getParameter("telefono"),
                                new FamiliaAnfitriona(
                                        rut,
                                        Short.parseShort(req.getParameter(
                                                "integrantes")),
                                        (short) 1
                                ),
                                new Usuario(
                                        rut,
                                        req.getParameter("nombreUsuario"),
                                        req.getParameter("clave1"),
                                        cu.establecerFechaActual(),
                                        "Familia"
                                )
                        );

                    }
                } catch (ParseException ex) {

                }
                break;

            case "registrar_alumno":
                String rut = req.getParameter("rut");
                try {
                    ProcesoValidarAlumnos port
                            = servicio.getProcesoValidarAlumnosPort();
                    cem.intercambios.controlador.cliente.Alumno encontrado
                            = port.verificarRutAlumno(rut);
                    if (encontrado != null) {
                        Persona nuevoRegistro
                                = cu.convertirAlumnoWebAPersonaJPA(
                                        encontrado);
                        nuevoRegistro.setUsuario(new Usuario(
                                encontrado.getRutPersona(),
                                req.getParameter("nombreUsuario"),
                                cu.encriptar(req.getParameter("clave1")),
                                encontrado.getFechaMatricula()
                                        .toGregorianCalendar()
                                        .getTime(),
                                "Alumno"));
                        try {
                            pf.create(nuevoRegistro);
                            req.setAttribute("mensajeEstado", "Nuevo usuario "
                                    + "registrado.");
                        } catch (Exception ex) {
                            req.setAttribute("mensajeEstado", "El rut ingresado"
                                    + " ya existe.");
                        }
                    } else {
                        req.setAttribute("mensajeEstado", "El rut ingresado"
                                + " no pertenece a un alumno matriculado en"
                                + " nuestra institución.");
                    }
                } catch (NoSuchAlgorithmException ex) {
                    mensaje = "Error de comunicación con el servicio web.";
                    LOG.log(Level.SEVERE, mensaje, ex);
                    req.setAttribute("mensajeEstado", mensaje);
                }
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                break;

            default:
                resp.sendRedirect("ingresar");
        }
    }

    private String verificarAccion(HttpServletRequest req)
            throws ServletException, IOException {
        return (req.getParameter("accion") == null)
                ? "" : req.getParameter("accion");
    }

}
