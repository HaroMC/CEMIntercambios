/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cem.intercambios.controlador.servlet;

import cem.intercambios.controlador.bean.AntecedenteFacade;
import cem.intercambios.controlador.bean.FamiliaAnfitrionaFacade;
import cem.intercambios.modelo.entidad.Antecedente;
import cem.intercambios.modelo.entidad.FamiliaAnfitriona;
import cem.intercambios.modelo.entidad.Usuario;
import cem.intercambios.modelo.utilidades.CemUtiles;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author FrancisBrianPC
 */
public class FamiliaAntecedenteServlet extends HttpServlet {

    @EJB
    AntecedenteFacade af;
    private HttpSession sesion;
    private CemUtiles cmu=new CemUtiles();
    @EJB
    FamiliaAnfitrionaFacade faf;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        obtenerSesionActiva(req, resp);
        Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        sesion=req.getSession();
        obtenerSesionActiva(req, resp);
        Usuario usuarioActual = (Usuario) sesion.getAttribute("usuarioActual");
        String archivourl = "C:\\Users\\FrancisBrianPC\\Desktop\\archivos";//lugar de almacenamiento de archivos. NO ES TEMPORAL, LIMPIEN SUS DISCOS DUROS CONCHASDESUMADRE
            PrintWriter out =resp.getWriter();
            DiskFileItemFactory factory = new DiskFileItemFactory();
            
            factory.setSizeThreshold(1024);
            
            factory.setRepository(new File(archivourl));
            
            ServletFileUpload upload = new ServletFileUpload(factory);
            
            
            try{
                
                List<FileItem> partes = upload.parseRequest(req);
                
                for(FileItem items: partes){
                    File file = new File(archivourl,items.getName());
                    items.write(file);
                    String contentType = items.getContentType();
                    long size = items.getSize();
                    String ubicacion= factory.getRepository().toString();
                   
                    String value = items.getName();
                    String fileName = new String(value.getBytes("ISO-8859-1"), "UTF-8");//esta parte permite el ingreso de datos con tildes, eñes, y caracteres varios aceptados por utf-8
                    sesion.setAttribute("fileName", fileName);
                    sesion.setAttribute("contentType", contentType);
                    sesion.setAttribute("size", size);
                    sesion.setAttribute("ubicacion", ubicacion);
                    
                    String rut =usuarioActual.getRutPersona(); //obtención de rut familia mediante sesión actual
                    FamiliaAnfitriona faa= faf.find(rut); //parseo a familiaanfitrionaFacade debido a llaves foráneas de jpql
                    Date fechaActual = cmu.establecerFechaActual();
                    Antecedente objAntecedentes = new Antecedente(
                                
//                                
                               af.codigoAuto(), fechaActual,fileName, contentType,ubicacion,faa);
                    
//                    if (dao.registrarArchivos(objArchivos,
////                                request.getParameter("fileName"),
////                                request.getParameter("ubicacion"),
////                                request.getParameter("contentType"))) {
//                            fileName,contentType, ubicacion)){
//                            
//                            request.getRequestDispatcher("/uploadfile.jsp")
//                                    .forward(request, response);
//                        }
//                        else {
//                            request.setAttribute("mensaje",
//                                    "Error de registro.");
//                            request.getRequestDispatcher("/uploadfile.jsp")
//                                    .forward(request, response);
//                        }
                    af.create(objAntecedentes);
//                    
//                                    
                    
                }
//            req.getRequestDispatcher("subidaArchivo.jsp").forward(req, resp);
            resp.sendRedirect("subidaArchivo.jsp");
                
            }catch(Exception e){
                System.out.print("Exception: "+e.getMessage()+"");
                RequestDispatcher rd = req.getRequestDispatcher("errorSubida.jsp");
                rd.forward(req, resp);
            }
    }

    private void obtenerSesionActiva(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        sesion = req.getSession();
        if (sesion.getAttribute("usuarioActual") == null) {
            resp.sendRedirect("../error/no_autorizado.jsp");
        }
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
