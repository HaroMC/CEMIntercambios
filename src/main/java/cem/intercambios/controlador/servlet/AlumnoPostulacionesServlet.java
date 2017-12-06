package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.AlumnoFacade;
import cem.intercambios.controlador.bean.FamiliaAnfitrionaFacade;
import cem.intercambios.controlador.bean.InscripcionAlumnoFacade;
import cem.intercambios.controlador.bean.InscripcionCelFacade;
import cem.intercambios.controlador.bean.ProgramaFacade;
import cem.intercambios.modelo.entidad.FamiliaAnfitriona;
import cem.intercambios.modelo.entidad.InscripcionAlumno;
import cem.intercambios.modelo.entidad.InscripcionCel;
import cem.intercambios.modelo.entidad.Usuario;
import cem.intercambios.modelo.utilidades.CemUtiles;
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

public class AlumnoPostulacionesServlet extends HttpServlet {

    private HttpSession sesion;
    private final CemUtiles cu = new CemUtiles();
    private static final Logger LOGGER
            = Logger.getLogger(AlumnoPostulacionesServlet.class.getName());

    @EJB
    private ProgramaFacade pf;
    @EJB
    private InscripcionCelFacade icf;
    @EJB
    private FamiliaAnfitrionaFacade faf;
    @EJB
    private InscripcionAlumnoFacade iaf;
    @EJB
    private AlumnoFacade af;

    //<editor-fold defaultstate="collapsed" desc=" GET ">
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        obtenerSesionActiva(req, resp);
        switch (verificarAccion(req)) {

            case "seleccionar_familia":
                String pais = req.getParameter("pais");
                List<FamiliaAnfitriona> listadoFamilias
                        = faf.familiasPorPais(pais);
                if (listadoFamilias != null) {
                    sesion.setAttribute("listadoFamilias", listadoFamilias);
                }
                req.getRequestDispatcher("seleccionar-familia.jsp")
                        .forward(req, resp);
                break;

            default:
                List<InscripcionCel> listadoProgramas
                        = icf.programasDisponiblesPorPaisConFamilias();
                if (listadoProgramas != null) {
                    sesion.setAttribute("listadoProgramas", listadoProgramas);
                }
                resp.sendRedirect("postulaciones.jsp");
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" POST ">
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        obtenerSesionActiva(req, resp);
        Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");

        switch (verificarAccion(req)) {

            case "confirmar_postulacion":
                InscripcionAlumno nuevaPostulacion = new InscripcionAlumno(
                        iaf.codigoAutoIncremental(),
                        cu.establecerFechaActual(),
                        pf.find(req.getParameter("programa")),
                        af.find(usuarioActual.getRutPersona()),
                        faf.find(req.getParameter("rutFamilia")),
                        (short) 1
                );
                iaf.create(nuevaPostulacion);
                String mensaje = "Se ha iniciado una nueva postulación.";
                LOGGER.info(mensaje);
                req.setAttribute("mensajeEstado", mensaje);
                resp.sendRedirect("inicializar-perfil");
                break;

            case "cancelar_postulacion":
                resp.sendRedirect("inicializar-perfil");
                break;

            default:
                resp.sendRedirect("inicializar-perfil");
        }

    }
    //</editor-fold>

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
