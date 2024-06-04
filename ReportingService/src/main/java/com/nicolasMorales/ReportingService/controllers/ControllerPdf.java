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
     * Genera informe PDF con los productos recibidos.
     * @param productos Recibe un listado de productos que se desean incluir en el informe.
     * @throws IOException Excepcion para manejar errores de la generacion del PDF.
     */
    @PostMapping(value = "/products/generate")
    public byte[] generatePDF(@RequestBody List<ProductDTO> productos) throws IOException {
        return pdfServ.generatePdfIngresos(productos).toByteArray();
    }
}
