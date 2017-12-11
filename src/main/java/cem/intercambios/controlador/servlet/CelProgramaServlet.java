package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.AlumnoFacade;
import cem.intercambios.controlador.bean.AsignaturaFacade;
import cem.intercambios.controlador.bean.CalificacionFacade;
import cem.intercambios.controlador.bean.InscripcionAlumnoFacade;
import cem.intercambios.controlador.bean.ProgramaFacade;
import cem.intercambios.modelo.entidad.Alumno;
import cem.intercambios.modelo.entidad.Asignatura;
import cem.intercambios.modelo.entidad.Calificacion;
import cem.intercambios.modelo.entidad.InscripcionAlumno;
import cem.intercambios.modelo.entidad.Programa;
import cem.intercambios.modelo.entidad.Usuario;
import cem.intercambios.modelo.utilidades.CemUtiles;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CelProgramaServlet extends HttpServlet {

    private static final Logger LOGGER
            = Logger.getLogger(CelProgramaServlet.class.getName());

    @EJB
    private AsignaturaFacade asif;

    @EJB
    private AlumnoFacade af;

    @EJB
    private CalificacionFacade calf;

    @EJB
    private InscripcionAlumnoFacade iaf;

    @EJB
    private ProgramaFacade pf;

    private final CemUtiles cu = new CemUtiles();

    private HttpSession sesion;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String accion = (req.getParameter("accion") == null
                ? "" : req.getParameter("accion"));

        sesion = req.getSession();
        Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");

        switch (accion) {

            default:
                List<Programa> programasInscritos = pf.programasInscritosCel(
                        usuarioActual.getRutPersona());
                sesion.setAttribute("programasInscritos", programasInscritos);
                resp.sendRedirect("calificaciones.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String codigoPrograma;
        sesion = req.getSession();
        Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");

        String accion = (req.getParameter("accion") == null
                ? "" : req.getParameter("accion"));

        switch (accion) {

            case "ver_notas_alumno":
                sesion.removeAttribute("alumnosDelPrograma");
                codigoPrograma = req.getParameter("codigoPrograma");
                List<InscripcionAlumno> alumnosDelPrograma
                        = iaf.alumnosPorProgramaIniciado(codigoPrograma);
                if (alumnosDelPrograma != null) {
                    if (!alumnosDelPrograma.isEmpty()) {
                        sesion.setAttribute("alumnosDelPrograma",
                                alumnosDelPrograma);
                    }
                }
                resp.sendRedirect("cel_programas");
                break;

            case "agregar_notas":
                String confirmar
                        = (req.getParameter("confirmar") == null
                        ? "" : req.getParameter("confirmar"));
                String rutAlumno = req.getParameter("rutAlumno");
                codigoPrograma = req.getParameter("codigoPrograma");

                Alumno alumnoCalificado = af.find(rutAlumno);

                switch (confirmar) {

                    case "si":
                        try {
                            Programa programaEdit = pf.find(codigoPrograma);
                            List<Asignatura> asignaturas = programaEdit.getAsignaturaList();

                            try {
                                Asignatura asig1 = asignaturas.get(0);
                                if (asig1 != null) {

                                    Calificacion calificacion1 = new Calificacion(
                                            calf.codigoAutoIncremental(),
                                            BigDecimal.valueOf(Double.parseDouble(req.getParameter("nota1"))),
                                            cu.establecerFechaActual(),
                                            alumnoCalificado,
                                            asig1
                                    );

                                    /*calf.create(calificacion1);
                                    calificacion1.setCodAsignatura(asig1);*/
                                    calf.create(calificacion1);

                                    //List<Calificacion> cali1 = asig1.getCalificacionList();
                                    //asif.edit(asig1);
                                    //calf.create(calificacion1);
                                }
                            } catch (Exception ex) {
                                String info = "Crash";
                                String info2 = info + "!";
                            }

                            try {
                                Asignatura asig2 = asignaturas.get(1);
                                if (asig2 != null) {
                                    Calificacion calificacion2 = new Calificacion(
                                            calf.codigoAutoIncremental(),
                                            BigDecimal.valueOf(Double.parseDouble(req.getParameter("nota2"))),
                                            cu.establecerFechaActual(),
                                            alumnoCalificado,
                                            asig2);
                                    calf.create(calificacion2);
                                }
                            } catch (Exception ex) {
                                String info = "Crash";
                                String info2 = info + "!";
                            }

                            try {
                                Asignatura asig3 = asignaturas.get(3);
                                if (asig3 != null) {
                                    Calificacion calificacion3 = new Calificacion(
                                            calf.codigoAutoIncremental(),
                                            BigDecimal.valueOf(Double.parseDouble(req.getParameter("nota3"))),
                                            cu.establecerFechaActual(),
                                            alumnoCalificado,
                                            asig3);
                                    calf.create(calificacion3);
                                }
                            } catch (Exception ex) {
                                String info = "Crash";
                                String info2 = info + "!";
                            }

                        } catch (Exception ex) {

                        }
                        sesion.removeAttribute("programaSe");
                        sesion.removeAttribute("asig1");
                        sesion.removeAttribute("asig2");
                        sesion.removeAttribute("asig3");
                        resp.sendRedirect("cel_programas");
                        break;

                    default:
                        Alumno alumnoSeleccionado = af.find(rutAlumno);
                        Programa programaSeleccionado = pf.find(codigoPrograma);
                        sesion.setAttribute("alumnoSe", alumnoSeleccionado);
                        sesion.setAttribute("programaSe", programaSeleccionado);
                        resp.sendRedirect("agregar_nota.jsp");
                }

            default:

        }
    }

}
