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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        sesion = req.getSession();
        List<InscripcionCel> listadoProgramas = icf.findByEstado((short) 2);

        if (listadoProgramas != null) {
            sesion.setAttribute("listadoProgramas", listadoProgramas);
        }

        resp.sendRedirect("postulaciones.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        sesion = req.getSession();
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

                resp.sendRedirect("seleccionar-familia.jsp");
                break;

            default:
                resp.sendRedirect("postulaciones.jsp");
        }

    }

}
