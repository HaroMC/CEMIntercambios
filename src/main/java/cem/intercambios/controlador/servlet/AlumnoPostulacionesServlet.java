package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.FamiliaAnfitrionaFacade;
import cem.intercambios.controlador.bean.InscripcionCelFacade;
import cem.intercambios.controlador.bean.ProgramaFacade;
import cem.intercambios.modelo.entidad.FamiliaAnfitriona;
import cem.intercambios.modelo.entidad.InscripcionCel;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AlumnoPostulacionesServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(AlumnoPostulacionesServlet.class.getName());

    @EJB
    private ProgramaFacade pf;

    @EJB
    private InscripcionCelFacade icf;

    @EJB
    private FamiliaAnfitrionaFacade faf;

    private HttpSession sesion;
    
    //<editor-fold defaultstate="collapsed" desc=" GET ">
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        obtenerSesionActiva(req, resp);
        String accion = ((req.getParameter("accion") == null)
                ? "" : req.getParameter("accion"));
        
        switch (accion) {
            
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
        switch (verificarAccion(req)) {
            
            case "confirmar_postulacion":
            
        }
        
    }
    //</editor-fold>

    private void obtenerSesionActiva(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        sesion = req.getSession();
        if (sesion == null) {
            resp.sendRedirect("../error/no-autorizado.jsp");
        }
    }
    
    private String verificarAccion(HttpServletRequest req) {
        return ((req.getParameter("accion") == null)
                ? "" : req.getParameter("accion"));
    }

}
