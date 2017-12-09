package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.AlumnoFacade;
import cem.intercambios.modelo.utilidades.CemUtiles;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActualizarAlumnosRegistradosServlet extends HttpServlet {

    private static final Logger LOG
            = Logger.getLogger(
                    ActualizarAlumnosRegistradosServlet.class.getName());

    @EJB
    private AlumnoFacade af;
    
    private final CemUtiles cu = new CemUtiles();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<String> rutAlumnosRegistrados = af.obtenerRuts();
        
        if (rutAlumnosRegistrados != null) {
            
        }
        
    }

}
