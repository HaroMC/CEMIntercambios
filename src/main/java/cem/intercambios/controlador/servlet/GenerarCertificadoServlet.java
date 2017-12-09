/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cem.intercambios.controlador.servlet;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PushbuttonField;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FrancisBrianPC
 */
public class GenerarCertificadoServlet extends HttpServlet {

    private HttpSession sesion;
    public static final String RESULT = "C:\\Users\\FrancisBrianPC\\Desktop\\archivos\\submit_me.pdf";
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
        obtenerSesionActiva(request, response);
        response.setContentType("text/html;charset=UTF-8");
        
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        obtenerSesionActiva(request, response);
        try {
            // Get the text that will be added to the PDF
            String text = request.getParameter("text");
            if (text == null || text.trim().length() == 0) {
                 text = "You didn't enter any text.";
            }
            // step 1
            Document document = new Document();
            // step 2
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            // step 3
            document.open();
            // step 4
            document.add(new Paragraph(String.format(
                "You have submitted the following text using the %s method:",
                request.getMethod())));
            document.add(new Paragraph(text));
            // step 5
            document.close();
 
            // setting some response headers
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control",
                "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            // setting the content type
            response.setContentType("application/pdf");
            // the contentlength
            response.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        }
        catch(DocumentException e) {
            throw new IOException(e.getMessage());
        }
    }
 
    /**
     * Manipulates a PDF file src with the file dest as result
     * @param src the original PDF
     * @param dest the resulting PDF
     * @throws IOException
     * @throws DocumentException
     */
    public void manipulatePdf(String src, String dest)
        throws IOException, DocumentException {
        
        // create a reader
        PdfReader reader = new PdfReader(src);
        // create a stamper
        PdfStamper stamper = new PdfStamper(reader,
            new FileOutputStream(dest));
        // create a submit button that posts the form as an HTML query string
        PushbuttonField button1 = new PushbuttonField(
            stamper.getWriter(), new Rectangle(90, 660, 140, 690), "post");
        button1.setText("POST");
        button1.setBackgroundColor(new GrayColor(0.7f));
        button1.setVisibility(PushbuttonField.VISIBLE_BUT_DOES_NOT_PRINT);
        PdfFormField submit1 = button1.getField();
        submit1.setAction(PdfAction.createSubmitForm(
            "/book/request", null,
            PdfAction.SUBMIT_HTML_FORMAT | PdfAction.SUBMIT_COORDINATES));
        // add the button
        stamper.addAnnotation(submit1, 1);
        // create a submit button that posts the form as FDF
        PushbuttonField button2 = new PushbuttonField(
            stamper.getWriter(), new Rectangle(200, 660, 250, 690), "FDF");
        button2.setBackgroundColor(new GrayColor(0.7f));
        button2.setText("FDF");
        button2.setVisibility(PushbuttonField.VISIBLE_BUT_DOES_NOT_PRINT);
        PdfFormField submit2 = button2.getField();
        submit2.setAction(PdfAction.createSubmitForm(
            "/book/request", null, PdfAction.SUBMIT_EXCL_F_KEY));
        // add the button
        stamper.addAnnotation(submit2, 1);
        // create a submit button that posts the form as XFDF
        PushbuttonField button3 = new PushbuttonField(
                stamper.getWriter(), new Rectangle(310, 660, 360, 690), "XFDF");
        button3.setBackgroundColor(new GrayColor(0.7f));
        button3.setText("XFDF");
        button3.setVisibility(PushbuttonField.VISIBLE_BUT_DOES_NOT_PRINT);
        PdfFormField submit3 = button3.getField();
        submit3.setAction(PdfAction.createSubmitForm(
            "/book/request", null, PdfAction.SUBMIT_XFDF));
        // add the button
        stamper.addAnnotation(submit3, 1);
        // create a reset button
        PushbuttonField button4 = new PushbuttonField(
            stamper.getWriter(), new Rectangle(420, 660, 470, 690), "reset");
        button4.setBackgroundColor(new GrayColor(0.7f));
        button4.setText("RESET");
        button4.setVisibility(PushbuttonField.VISIBLE_BUT_DOES_NOT_PRINT);
        PdfFormField reset = button4.getField();
        reset.setAction(PdfAction.createResetForm(null, 0));
        // add the button
        stamper.addAnnotation(reset, 1);
        // close the stamper
        stamper.close();
        reader.close();
    }
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 6067021675155015602L;
 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        service(request, response);
        obtenerSesionActiva(request, response);
        try {
            manipulatePdf(RESULT, RESULT);
        } catch (DocumentException ex) {
            Logger.getLogger(GenerarCertificadoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        obtenerSesionActiva(request, response);
    }

    private void obtenerSesionActiva(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        sesion = req.getSession();
        if (sesion.getAttribute("usuarioActual") == null) {
            resp.sendRedirect("../error/no-autorizado.jsp");
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
