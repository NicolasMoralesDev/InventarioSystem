package com.nicolasMorales.ReportingService.controllers;

import com.nicolasMorales.ReportingService.dtos.ProductDTO;
import com.nicolasMorales.ReportingService.services.impl.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Controller de Reportes PDF para Productos.
 * @author Nicolas Morales.
 */
@RestController
@RequestMapping("/api/v1/pdf")
@CrossOrigin(origins = "*")
public class ControllerPdf {

    @Autowired
    private PdfService pdfServ;

    /**
     * Genera un informe PDF con los productos enviados.
     * @param response Retorna el PDF
     * @param productos Recibe un listado de productos que se desean incluir en el informe.
     * @throws IOException Excepcion para manejar errores de la generacion del PDF.
     */
    @PostMapping( value = "/productos/generate")
    public void generatePDF(HttpServletResponse response, @RequestBody List<ProductDTO> productos) throws IOException {
        byte[] pdfBytes = pdfServ.generatePdfIngresos(productos).toByteArray();

        // Set response headers
        response.setContentType("application/pdf");
        response.setContentLength(pdfBytes.length);
        response.setHeader("Content-Disposition", "attachment; filename=product_table.pdf");

        // Escribe el PDF con la informacion a retornar
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(pdfBytes);
        outputStream.flush();
        outputStream.close();
    }
}
