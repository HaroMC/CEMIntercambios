package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.CentroEstudiosLocalFacade;
import cem.intercambios.controlador.bean.InscripcionCelFacade;
import cem.intercambios.controlador.bean.ProgramaFacade;
import cem.intercambios.modelo.entidad.InscripcionCel;
import cem.intercambios.modelo.entidad.Programa;
import cem.intercambios.modelo.entidad.Usuario;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CelPostulacionesServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(CelPostulacionesServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private ProgramaFacade pf;

    @EJB
    private InscripcionCelFacade icf;

    @EJB
    private CentroEstudiosLocalFacade celf;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        obtenerSesionActiva(req, resp);
        Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");

        List<Programa> programasDisponibles
                = pf.programasDisponiblesSinPostular(
                        usuarioActual.getRutPersona());
        
        if (programasDisponibles != null) {
            sesion.setAttribute("programasDisponibles", programasDisponibles);
        }
        
        List<InscripcionCel> programasInscritos = icf.programasInscritosCel(
                usuarioActual.getRutPersona());
        if (programasInscritos != null) {
            sesion.setAttribute("programasInscritos", programasInscritos);
        }

        resp.sendRedirect("consultar-postulaciones.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        obtenerSesionActiva(req, resp);
        Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");

        switch (verificarAccion(req)) {

            case "postular":
                InscripcionCel nuevaPostulacion = new InscripcionCel(
                        icf.codigoAutoIncremental(),
                        establecerFechaActual(),
                        pf.find(req.getParameter("codigoPrograma")),
                        celf.find(usuarioActual.getRutPersona()),
                        (short) 1
                );
                icf.create(nuevaPostulacion);
                String mensaje = "Se ha iniciado una nueva postulación.";
                LOGGER.info(mensaje);
                req.setAttribute("mensajeEstado", mensaje);
                resp.sendRedirect("inscripciones");
                break;

            case "cancelar_postulacion":
                resp.sendRedirect("inicializar-perfil");
                break;

            default:
                resp.sendRedirect("inicializar-perfil");
        }
    }

    private Date establecerFechaActual() {
        try {
            DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendario = Calendar.getInstance();
            int dia = calendario.get(Calendar.DAY_OF_MONTH),
                    mes = calendario.get(Calendar.MONTH) + 1,
                    ano = calendario.get(Calendar.YEAR);
            Date fechaActual = formatoFecha.parse(ano + "-" + mes + "-" + dia);
            return fechaActual;
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, "Error en el parseo de la fecha actual.",
                    ex);
            return null;
        }
    }

    private void obtenerSesionActiva(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        sesion = req.getSession();
        if (sesion.getAttribute("usuarioActual") == null) {
            resp.sendRedirect("../error/no-autorizado.jsp");
        }
    }

    private String verificarAccion(HttpServletRequest req) {
        return ((req.getParameter("accion") == null)
                ? "" : req.getParameter("accion"));
    }
    
}
