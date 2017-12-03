package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.AlumnoFacade;
import cem.intercambios.modelo.entidad.Alumno;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletPruebas", urlPatterns = {"/pruebas"})
public class ServletPruebas extends HttpServlet {
    
    @EJB
    private AlumnoFacade af;
    
    private HttpSession sesion;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        sesion = req.getSession(true);
        
        List<Alumno> alumnos = af.findAll();
        
        sesion.setAttribute("alumnos", alumnos);
        
        resp.sendRedirect("consultar-notas-test.jsp");
        //req.getRequestDispatcher("consultar-notas-test.jsp").forward(req, resp);
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
                
        Alumno notasAlumno = af.buscarNotas(
                "Inscrito", req.getParameter("rut"));
        
        sesion.setAttribute("alumnos", notasAlumno);
        
        resp.sendRedirect("consultar-notas-test.jsp");
        //req.getRequestDispatcher("consultar-notas-test.jsp").forward(req, resp);
        
    }
    
}
