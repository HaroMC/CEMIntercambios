package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.InscripcionCelFacade;
import cem.intercambios.controlador.bean.ProgramaFacade;
import cem.intercambios.modelo.entidad.InscripcionCel;
import cem.intercambios.modelo.entidad.Programa;
import cem.intercambios.modelo.entidad.Usuario;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CelPostulacionesServlet extends HttpServlet {

    private HttpSession sesion;

    @EJB
    private ProgramaFacade pf;

    @EJB
    private InscripcionCelFacade icf;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        sesion = req.getSession();
        Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");

        List<Programa> programasDisponibles = pf.findByEstado((short) 1);
        if (programasDisponibles != null) {
            sesion.setAttribute("programasDisponibles",
                    programasDisponibles);
        }

        //List<Programa> programasInscritos = pf.programasInscritosCel(usuarioActual.getRutPersona());
        List<InscripcionCel> programasInscritos = icf.programasInscritosCel(
                usuarioActual.getRutPersona());
        if (programasInscritos != null) {
            sesion.setAttribute("programasInscritos",
                    programasInscritos);
        }

        resp.sendRedirect("consultar-postulaciones.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException { }

}
