package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.AsignaturaFacade;
import cem.intercambios.controlador.bean.ProgramaFacade;
import cem.intercambios.modelo.entidad.Asignatura;
import cem.intercambios.modelo.entidad.Programa;
import cem.intercambios.modelo.utilidades.CemUtiles;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
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
 * @version 1.0.1
 * @since 2017-12-01
 */
public class CemProgramaServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(CemProgramaServlet.class.getName());

    private HttpSession sesion;

    private final CemUtiles cu = new CemUtiles();

    @EJB
    private ProgramaFacade pf;

    @EJB
    private AsignaturaFacade af;

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
                    try {
                        pf.create(programaRegistrado);
                        mensaje = "Programa registrado correctamente.";
                        LOGGER.info(mensaje);
                        req.setAttribute("mensajeEstado", mensaje);
                        resp.sendRedirect("cem-programas");
                    } catch (Exception ex) {
                        LOGGER.log(Level.SEVERE,
                                "Error en la inserción. La llave única ya "
                                + "existe en la base de datos.", ex);
                        req.setAttribute("mensajeEstado",
                                "Se ha producido un error al registrar el "
                                + "programa.");
                        resp.sendRedirect("agregar-programa.jsp");
                    }
                }
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
        
        String descrpcionUno = req.getParameter("descrpcion1");
        String descrpcionDos = req.getParameter("descrpcion2");
        String descrpcionTres = req.getParameter("descrpcion3");

        Programa nuevoPrograma = new Programa(
                cu.codigoPrograma(nombrePrograma, tipoDuracion, cupos),
                nombrePrograma,
                Long.parseLong(valor),
                Long.parseLong(cupos),
                (short) 1
        );

        nuevoPrograma = definirFechas(nuevoPrograma, tipoDuracion);

        List<Asignatura> asignaturas = new ArrayList<>();

        if (asignaturaUno.length() > 0) {
            asignaturas.add(
                    crearAsignatura(asignaturaUno, descrpcionUno,
                            nuevoPrograma));
        }

        if (asignaturaDos.length() > 0) {
            asignaturas.add(
                    crearAsignatura(asignaturaUno, descrpcionDos,
                            nuevoPrograma));
        }

        if (asignaturaTres.length() > 0) {
            asignaturas.add(
                    crearAsignatura(asignaturaUno, descrpcionTres,
                            nuevoPrograma));
        }

        if (!asignaturas.isEmpty()) {
            nuevoPrograma.setAsignaturaList(asignaturas);
        }

        return nuevoPrograma;
    }

    private void ordenarLista(List<Programa> listadoProgramas) {
        Collections.sort(listadoProgramas, new Comparator<Programa>() {
            @Override
            public int compare(Programa p1, Programa p2) {
                return p1.getCodigo().compareTo(p2.getCodigo());
            }
        });
    }

    private Asignatura crearAsignatura(String asignatura, String descripcion,
            Programa programa) {
        return new Asignatura(cu.codigoAsignatura(asignatura), asignatura,
                descripcion, programa);
    }

}
