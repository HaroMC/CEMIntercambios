package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.PersonaFacade;
import cem.intercambios.controlador.bean.UsuarioFacade;
import cem.intercambios.modelo.entidad.FamiliaAnfitriona;
import cem.intercambios.modelo.entidad.Persona;
import cem.intercambios.modelo.entidad.Usuario;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrarCuentaServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(RegistrarCuentaServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private PersonaFacade pf;

    @EJB
    private UsuarioFacade uf;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String estado, tipo = req.getParameter("tipo");
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");

        if (tipo != null) {
            switch (tipo.toLowerCase()) {
                case "familia":
                    estado = "Aprobación pendiente.";
                    try {
                        String rut = req.getParameter("rut");
                        // Si el rut no está registrado en la base de datos,
                        // entonces permite el registro, sino, no realiza
                        // acción.
                        if (pf.find(rut) == null) {

                            Calendar cal = Calendar.getInstance();
                            Date fechaRegistro = formatoFecha.parse(
                                    Integer.toString(cal.get(Calendar.YEAR)) + "-"
                                    + Integer.toString(cal.get(Calendar.MONTH)) + "-"
                                    + Integer.toString(cal.get(Calendar.DAY_OF_MONTH))
                            );

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
                                            fechaRegistro,
                                            "Familia"
                                    )
                            );

                        }
                    } catch (ParseException ex) {

                    }
                    break;

                case "alumno":
                    break;

                default:

            }
        }
    }

}
