package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.ProgramaFacade;
import cem.intercambios.modelo.entidad.Programa;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditarProgramaProceso extends HttpServlet {
        
    @EJB
    private ProgramaFacade pf;
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        String estado = request.getParameter("estado");
        Programa p = (Programa) request.getSession().getAttribute("pEditado");
        p.setEstado(estado);
        
        
        pf.edit(p);
        
        
        request.getSession().removeAttribute("pEditado");
        response.sendRedirect("cem-programas");
        
    }
}
