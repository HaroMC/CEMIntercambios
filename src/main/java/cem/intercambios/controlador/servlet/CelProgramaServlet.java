package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.ProgramaFacade;
import cem.intercambios.modelo.entidad.Programa;
import cem.intercambios.modelo.entidad.Usuario;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CelProgramaServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(CelProgramaServlet.class.getName());
    
    @EJB
    private ProgramaFacade pf;

    private HttpSession sesion;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        sesion = req.getSession();
        Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
        List<Programa> programasInscritos = pf.programasInscritosCel(
                usuarioActual.getRutPersona());
        sesion.setAttribute("programasInscritos", programasInscritos);
        resp.sendRedirect("calificaciones.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

}
