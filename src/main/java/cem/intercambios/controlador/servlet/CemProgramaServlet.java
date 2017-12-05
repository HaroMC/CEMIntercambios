package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.ProgramaFacade;
import cem.intercambios.modelo.entidad.Programa;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
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
 * @version 1.0.1
 * @since 2017-12-01
 */
public class CemProgramaServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(CemProgramaServlet.class.getName());

    private HttpSession sesion;

    @EJB
    private ProgramaFacade pf;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        //<editor-fold defaultstate="collapsed" desc="Caso : Listar">
        String mensaje;
        sesion = req.getSession();
        List<Programa> listadoProgramas = pf.findAll();
        if (listadoProgramas != null) {
            ordenarLista(listadoProgramas);
            sesion.setAttribute("listadoProgramas", listadoProgramas);
            mensaje = "Visualización correcta de todos los programas.";
            LOGGER.info(mensaje);
        } else {
            mensaje = "No se encontraron registros.";
            sesion.setAttribute("mensajeEstado", mensaje);
            LOGGER.info(mensaje);
        }
        resp.sendRedirect("administrar-programas.jsp");
        //</editor-fold>

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String mensaje;
        String accion = ((req.getParameter("accion") == null)
                ? "" : req.getParameter("accion"));
        sesion = req.getSession();
        Programa programaRegistrado;

        switch (accion) {

            //<editor-fold defaultstate="collapsed" desc="Caso : Eliminar">
            case "eliminar":
                /*pf.remove(pf.find(BigDecimal.valueOf(Long.parseLong(
                        req.getParameter("codigo")
                ))));*/
                pf.remove(pf.find(req.getParameter("codigo")));
                mensaje = "Programa eliminado correctamente.";
                LOGGER.info(mensaje);
                sesion.setAttribute("mensajeEstado", mensaje);
                resp.sendRedirect("cem-programas");
                break;
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Caso : Agregar">
            case "agregar":
                programaRegistrado = definirPrograma(req);

                if (programaRegistrado != null) {
                    pf.create(programaRegistrado);
                    mensaje = "Programa registrado correctamente.";
                    LOGGER.info(mensaje);
                    req.setAttribute("mensajeEstado", mensaje);
                } else {
                    mensaje = "Se ha producido un error al registrar el "
                            + "programa.";
                    req.setAttribute("mensajeEstado", mensaje);
                    LOGGER.info(mensaje);
                }

                resp.sendRedirect("cem-programas");
                break;
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Caso : Modificar">
            case "modificar":
                Programa pEditar;
                String confirmarModificacion
                        = (req.getParameter("confirmar") == null
                        ? "" : req.getParameter("confirmar"));

                switch (confirmarModificacion) {

                    case "si":
                        pEditar = (Programa) sesion.getAttribute("pEditar");
                        pEditar.setNombrePrograma(
                                req.getParameter("nombrePrograma"));
                        pEditar.setCupos(
                                Long.parseLong(req.getParameter("cupos")));
                        pEditar.setValor(
                                Long.parseLong(req.getParameter("valor")));
                        pEditar.setEstado(
                                Short.parseShort(req.getParameter("estado")));
                        pEditar = definirFechas(pEditar,
                                req.getParameter("tipoDuracion"));
                        pf.edit(pEditar);
                        sesion.removeAttribute("pEditar");
                        resp.sendRedirect("cem-programas");
                        break;

                    default:
                        pEditar = pf.find(req.getParameter("codigo"));
                        sesion.setAttribute("pEditar", pEditar);
                        resp.sendRedirect("editar-programa.jsp");
                }
                break;
            //</editor-fold>

            default:
                resp.sendRedirect("cem-programas");
        }
    }

    private Programa definirFechas(Programa programa, String tipoDuracion) {

        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendario = Calendar.getInstance();
        int dia, mes, ano;
        Date fechaInicio, fechaTermino;

        switch (tipoDuracion.toLowerCase()) {

            // Desde el 10 de Agosto al 10 de Octubre.
            case "normal":
                try {
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
                    programa.setFechaInicio(fechaInicio);
                    programa.setFechaTermino(fechaTermino);
                } catch (ParseException ex) {
                    LOGGER.log(Level.SEVERE,
                            "Error en el parseo de la fecha actual.", ex);
                }
                break;

            // Desde el 15 de Enero al 15 de Febrero.
            case "corto":
                try {
                    dia = 15;
                    mes = Calendar.JANUARY + 1;
                    ano = calendario.get(Calendar.YEAR) + 1;
                    fechaInicio = formatoFecha.parse(
                            ano + "-" + mes + "-" + dia);
                    mes = Calendar.FEBRUARY + 1;
                    fechaTermino = formatoFecha.parse(
                            ano + "-" + mes + "-" + dia);
                    programa.setFechaInicio(fechaInicio);
                    programa.setFechaTermino(fechaTermino);
                } catch (ParseException ex) {
                    LOGGER.log(Level.SEVERE,
                            "Error en el parseo de la fecha actual.", ex);
                }
        }
        return programa;
    }

    private Programa definirPrograma(HttpServletRequest req)
            throws ServletException, IOException {

        String nombrePrograma = req.getParameter("nombrePrograma");
        String tipoDuracion = req.getParameter("tipoDuracion");
        String valor = req.getParameter("valor");
        String cupos = req.getParameter("cupos");

        String asignaturaUno = req.getParameter("asignatura1");
        String asignaturaDos = req.getParameter("asignatura2");
        String asignaturaTres = req.getParameter("asignatura3");

        Programa nuevoPrograma = new Programa(
                // Definir un código correctamente!!
                "PRO-".concat(Integer.toString(pf.count() + 1)),
                nombrePrograma,
                Long.parseLong(valor),
                Long.parseLong(cupos),
                (short) 1
        );

        nuevoPrograma = definirFechas(nuevoPrograma, tipoDuracion);
        return nuevoPrograma;
    }
    
    
    private String definirCodigo(String nombrePrograma) {
        
        int random = ThreadLocalRandom.current().nextInt(1, 100000);
        
        int largo;
        
        StringBuilder sb = new StringBuilder();
        
        String[] palabras = nombrePrograma.split(" ");
        
        sb.append("PR").append("-");
        
        for (String palabra : palabras) {
            
            sb.append(palabra.charAt(0));
            
        }
        
        return "";
    }
    
    
    
    private void ordenarLista(List<Programa> listadoProgramas) {
        Collections.sort(listadoProgramas, new Comparator<Programa>() {
            @Override
            public int compare(Programa p1, Programa p2) {
                return p1.getCodigo().compareTo(p2.getCodigo());
            }
        });
    }
    
}
