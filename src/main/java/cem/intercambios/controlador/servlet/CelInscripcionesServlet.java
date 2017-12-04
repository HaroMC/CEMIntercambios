package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.ProgramaFacade;
import cem.intercambios.modelo.entidad.InscripcionCel;
import cem.intercambios.modelo.entidad.Programa;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CelInscripcionesServlet extends HttpServlet {
    
    private HttpSession sesion;
    
    @EJB
    private ProgramaFacade pf;
    
    @EJB
    private InscripcionCel icf;
    
        
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        sesion = req.getSession();
        List<Programa> programasDisponibles = pf.findByEstado((short) 1);
        String mensaje;
        
        if (programasDisponibles.isEmpty()) {
            mensaje = "Búsqueda sin resultados.";
            // Log
            sesion.setAttribute("mensajeResultados", mensaje);
        }
        
        sesion.setAttribute("programasDisponibles", programasDisponibles);
        resp.sendRedirect("postulaciones.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        
    }
    
}
