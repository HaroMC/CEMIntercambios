package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.ProgramaFacade;
import cem.intercambios.modelo.entidad.Programa;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HaroMC
 * @version 1.0.0
 * @since 2017-12-01
 */
public class CemProgramaServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(IniciarSesionServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private ProgramaFacade pf;

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String mensaje;
        sesion = request.getSession();

        List<Programa> listadoProgramas = pf.findAll();
        if (listadoProgramas != null) {
            // Inicio: Ordenamiento ascendente de programas por código.
            Collections.sort(listadoProgramas,
                    new Comparator<Programa>() {
                @Override
                public int compare(Programa p1, Programa p2) {
                    return p1.getCodigo().intValue()
                            - p2.getCodigo().intValue();
                }
            });
            // Fin
            sesion.setAttribute("listadoProgramas", listadoProgramas);
            mensaje = "Visualización correcta de todos los programas.";
            LOGGER.info(mensaje);
        } else {
            mensaje = "No se encontraron registros.";
            sesion.setAttribute("mensajeEstado", mensaje);
            LOGGER.info(mensaje);
        }
        response.sendRedirect("administrar-programas.jsp");
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String mensaje, accion = request.getParameter("accion");
        sesion = request.getSession();
        Programa programaRegistrado;

        switch (accion) {

            case "eliminar":
                pf.remove(pf.find(BigDecimal.valueOf(Long.parseLong(
                        request.getParameter("codigo")
                ))));
                mensaje = "Programa eliminado correctamente.";
                LOGGER.info(mensaje);
                sesion.setAttribute("mensajeEstado", mensaje);
                response.sendRedirect("cem-programas");
                break;

            case "agregar":
                programaRegistrado = definirPrograma(request);
                if (programaRegistrado != null) {
                    pf.create(programaRegistrado);
                    mensaje = "Programa registrado correctamente.";
                    LOGGER.info(mensaje);
                    request.setAttribute("mensajeEstado", mensaje);
                } else {
                    mensaje = "Se ha producido un error al registrar el "
                            + "programa.";
                    request.setAttribute("mensajeEstado", mensaje);
                    LOGGER.info(mensaje);
                }
                response.sendRedirect("cem-programas");
                break;

            case "modificar":
                Programa programaEditado = pf.find(BigDecimal.valueOf(
                        Long.parseLong(request.getParameter("codigo"))
                ));

                sesion.setAttribute("pEditado", programaEditado);

                request.getRequestDispatcher("editar-programa.jsp")
                        .forward(request, response);

        }
        //response.sendRedirect("cem-programas");
    }

    /**
     *
     * @param request
     * @return
     * @throws ServletException
     * @throws IOException
     */
    private Programa definirPrograma(HttpServletRequest request)
            throws ServletException, IOException {

        String nombrePrograma = request.getParameter("nombrePrograma");
        String tipoDuracion = request.getParameter("tipoDuracion");
        String valor = request.getParameter("valor");

        String asignaturaUno = request.getParameter("asignatura1");
        String asignaturaDos = request.getParameter("asignatura2");
        String asignaturaTres = request.getParameter("asignatura3");

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendario = Calendar.getInstance();

        Programa nuevoPrograma = null;

        int dia, mes, ano;
        Date fechaInicio, fechaTermino;

        switch (tipoDuracion.toLowerCase()) {

            case "normal": // Desde el 10 de Agosto al 10 de Octubre.
                try {
                    nuevoPrograma = new Programa(
                            pf.codigoAutoIncremental(),
                            nombrePrograma,
                            Integer.parseInt(valor),
                            "Sin CEL asignado"
                    );

                    if (calendario.get(Calendar.MONTH) >= Calendar.AUGUST) {
                        ano = calendario.get(Calendar.YEAR) + 1;
                    } else {
                        ano = calendario.get(Calendar.YEAR);
                    }

                    dia = 10;
                    mes = Calendar.AUGUST + 1;
                    fechaInicio = formatoFecha.parse(
                            ano + "-" + mes + "-" + dia);

                    mes = Calendar.OCTOBER + 1;
                    fechaTermino = formatoFecha.parse(
                            ano + "-" + mes + "-" + dia);

                    nuevoPrograma.setFechaInicio(fechaInicio);
                    nuevoPrograma.setFechaTermino(fechaTermino);

                } catch (ParseException ex) {
                    LOGGER.log(Level.SEVERE,
                            "Error en el parseo de la fecha actual.", ex);
                }
                break;

            case "corto": // Desde el 15 de Enero al 15 de Febrero.
                try {
                    nuevoPrograma = new Programa(
                            pf.codigoAutoIncremental(),
                            nombrePrograma,
                            Integer.parseInt(valor),
                            "Sin CEL asignado"
                    );

                    dia = 15;
                    mes = Calendar.JANUARY + 1;
                    ano = calendario.get(Calendar.YEAR) + 1;

                    fechaInicio = formatoFecha.parse(
                            ano + "-" + mes + "-" + dia);

                    mes = Calendar.FEBRUARY + 1;
                    fechaTermino = formatoFecha.parse(
                            ano + "-" + mes + "-" + dia);

                    nuevoPrograma.setFechaInicio(fechaInicio);
                    nuevoPrograma.setFechaTermino(fechaTermino);

                } catch (ParseException ex) {
                    LOGGER.log(Level.SEVERE,
                            "Error en el parseo de la fecha actual.", ex);
                }
        }

        // Definir asignaturas.
        return nuevoPrograma;
    }

}
