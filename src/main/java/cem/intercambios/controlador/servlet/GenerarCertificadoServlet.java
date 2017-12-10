package cem.intercambios.controlador.servlet;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GenerarCertificadoServlet extends HttpServlet {

    private static final long serialVersionUID = 6067021675155015602L;

    private HttpSession sesion;

    public static final String RESULT
            = "C:\\Users\\FrancisBrianPC\\Desktop\\archivos\\submit_me.pdf";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        obtenerSesionActiva(req, resp);
        try {
            // Get the text that will be added to the PDF
            String text = req.getParameter("text");
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
                    "You have submitted the following text using the %s "
                    + "method:",
                    req.getMethod())));
            document.add(new Paragraph(text));
            // step 5
            document.close();
            // setting some response headers
            resp.setHeader("Expires", "0");
            resp.setHeader("Cache-Control",
                    "must-revalidate, post-check=0, pre-check=0");
            resp.setHeader("Pragma", "public");
            // setting the content type
            resp.setContentType("application/pdf");
            // the contentlength
            resp.setContentLength(baos.size());
            // write ByteArrayOutputStream to the ServletOutputStream
            OutputStream os = resp.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        } catch (DocumentException e) {
            throw new IOException(e.getMessage());
        }
    }

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
                stamper.getWriter(), new Rectangle(420, 660, 470, 690),
                "reset");
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        service(req, resp);
        obtenerSesionActiva(req, resp);
        try {
            manipulatePdf(RESULT, RESULT);
        } catch (DocumentException ex) {
            Logger.getLogger(GenerarCertificadoServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        obtenerSesionActiva(req, resp);
    }

    private void obtenerSesionActiva(HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException, IOException {
        sesion = req.getSession();
        if (sesion.getAttribute("usuarioActual") == null) {
            resp.sendRedirect("../error/no-autorizado.jsp");
        }
    }

}
