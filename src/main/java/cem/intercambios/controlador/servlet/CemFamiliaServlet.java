/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Bugueño
 */
public class CemFamiliaServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(IniciarSesionServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private FamiliaAnfitrionaFacade faf;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         sesion = request.getSession();
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
        response.sendRedirect("administrar-familias.jsp");
       
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
