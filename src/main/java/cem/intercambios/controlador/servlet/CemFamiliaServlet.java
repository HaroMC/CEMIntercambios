package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.FamiliaAnfitrionaFacade;
import cem.intercambios.modelo.entidad.FamiliaAnfitriona;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CemFamiliaServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(CemFamiliaServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private FamiliaAnfitrionaFacade faf;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        sesion = req.getSession();
        String mensaje;
        List<FamiliaAnfitriona> listadoFamilias = faf.findAll();

        if (listadoFamilias != null) {
            sesion.setAttribute("listadoFamilias", listadoFamilias);
            mensaje = "Visualización correcta de todos las familias.";
            LOGGER.info(mensaje);
        } else {
            mensaje = "No se encontraron registros.";
            sesion.setAttribute("mensajeEstado", mensaje);
            LOGGER.info(mensaje);
        }
        resp.sendRedirect("administrar_familias.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        sesion = req.getSession();
        String accion = (req.getParameter("accion") == null
                ? "" : req.getParameter("accion"));

        switch (accion) {

            case "modificar":
                FamiliaAnfitriona familiaEditar;
                String confirmarModificacion
                        = (req.getParameter("confirmar") == null
                        ? "" : req.getParameter("confirmar"));
                switch (confirmarModificacion) {
                    case "si":
                        familiaEditar = (FamiliaAnfitriona) sesion.getAttribute(
                                "familiaEditar");
                        try {
                            familiaEditar.setEstado(Short.parseShort(
                                    req.getParameter("estado")));
                        } catch (NumberFormatException ex) {

                        }
                        faf.edit(familiaEditar);
                        sesion.removeAttribute("familiaEditar");
                        resp.sendRedirect("administrar_familias");
                        break;

                    default:
                        String rutFamilia = req.getParameter("rutFamilia");
                        familiaEditar = faf.find(rutFamilia);
                        sesion.setAttribute("familiaEditar", familiaEditar);
                        resp.sendRedirect("actualizar_familia.jsp");
                }
                break;

        }

    }

}
