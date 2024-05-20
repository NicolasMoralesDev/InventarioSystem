package com.nicolasMorales.ReportingService.controllers;

import com.nicolasMorales.ReportingService.services.impl.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller de Reportes PDF.
 * @author Nicolas Morales.
 */
@RestController
@RequestMapping("/api/v1/pdf")
@CrossOrigin(origins = "*")
public class ControllerPdf {

    @Autowired
    private PdfService pdfServ;

    @PostMapping("/generate")
    public void generatePDF(HttpServletResponse response) throws IOException {
        byte[] pdfBytes = pdfServ.generatePdf().toByteArray();

        // Set response headers
        response.setContentType("application/pdf");
        response.setContentLength(pdfBytes.length);
        response.setHeader("Content-Disposition", "attachment; filename=product_table.pdf");

        // Write the PDF data to the response
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(pdfBytes);
        outputStream.flush();
        outputStream.close();
    }
}
