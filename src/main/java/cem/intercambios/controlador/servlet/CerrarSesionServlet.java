package cem.intercambios.controlador.servlet;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CerrarSesionServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(CerrarSesionServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        req.getSession().invalidate();
        LOGGER.info("Sesión finalizada correctamente.");
        resp.sendRedirect("./ingresar");
    }

}
