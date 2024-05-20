package com.nicolasMorales.ReportingService.services.impl;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.nicolasMorales.ReportingService.services.IPdfService;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class  PdfService implements IPdfService {
    @Override
    public ByteArrayOutputStream generatePdf() throws IOException  {

     // Declaración del array
     String[] producto = new String[8];

     // Inicialización de las propiedades
     producto[0] = "C001"; // Codigo
     producto[1] = "Camisa a cuadros"; // Nombre
     producto[2] = "Camisa de manga larga a cuadros para hombre"; // Descripción
     producto[3] = "Ropa"; // Categoría
     producto[4] = "Camisas"; // Subcategoría
     producto[5] = "1"; // Cantidad
     producto[6] = "25.99"; // Precio
     producto[7] = "Tommy Hilfiger"; // Marca

     String rutaArchivo = "tabla.pdf";

     // Creación del documento PD
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
     PdfWriter pdf = new PdfWriter(byteArrayOutputStream);
     PdfDocument pdfDocument = new PdfDocument(pdf);


     Document document = new Document(pdfDocument, PageSize.A4.rotate());

     document.setMargins(20, 20, 20, 20);
     PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
     PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
     Table table = new Table(new float[]{4, 1, 3, 4, 3, 3, 3, 3});
     table.setWidth(UnitValue.createPercentValue(100));


        table.addCell("Codigo");
        table.addCell("Nombre");
        table.addCell("Descripcion");
        table.addCell("Categoria");
        table.addCell("Sub categoria");
        table.addCell("Marca");
        table.addCell("Precio");
        table.addCell("Cantidad");

// Iterate through the product array, extracting and adding data to the table
     for (String product : producto) {
         table.addCell(product); // Código
     }

     document.add(table);
     document.close();
     return byteArrayOutputStream;

    }
}
