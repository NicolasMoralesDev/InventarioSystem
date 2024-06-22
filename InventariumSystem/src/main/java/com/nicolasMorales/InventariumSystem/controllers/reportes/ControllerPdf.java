package com.nicolasMorales.InventariumSystem.controllers.reportes;

import com.nicolasMorales.InventariumSystem.dto.ProductDTO;
import com.nicolasMorales.InventariumSystem.services.impl.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Controller de Reportes PDF para Productos.
 * @author Nicolas Morales.
 */
//@RestController
//@RequestMapping("/api/v1/pdf")
//@CrossOrigin(origins = "*")
public class ControllerPdf {
}
//    @Autowired
//    private PdfService pdfServ;
//
//    private final Path fileStorageLocation = Paths.get("pdf-storage").toAbsolutePath().normalize();
//
//    public void PdfController() {
//        try {
//            Files.createDirectories(this.fileStorageLocation);
//        } catch (Exception ex) {
//            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
//        }
//    }

//    /**
//     * Genera informe PDF con los productos recibidos.
//     * @param productos Recibe un listado de productos que se desean incluir en el informe.
//     * @throws IOException Excepcion para manejar errores de la generacion del PDF.
//     */
//    @PostMapping(value = "/products/generate")
//    public ResponseEntity<Map<String, String>> generatePDF(@RequestBody List<ProductDTO> productos) throws IOException {
//        try {
//            ByteArrayOutputStream pdfContent = pdfServ.generatePdfIngresos(productos);
//            String fileName = "products_report_" + UUID.randomUUID() + ".pdf";
//            Path targetLocation = this.fileStorageLocation.resolve(fileName);
//            Files.write(targetLocation, pdfContent.toByteArray());
//            String fileDownloadUri = "http://localhost:9005" + "/api/v1/pdf/download/"+ fileName;
//
//            Map<String, String> response = new HashMap<>();
//            response.put("url", fileDownloadUri);
//
//            return ResponseEntity.ok(response);
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

//    @GetMapping("/download/{fileName:.+}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
//        try {
//            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//            if (resource.exists()) {
//                return ResponseEntity.ok()
//                        .contentType(MediaType.APPLICATION_PDF)
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                        .body(resource);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//
//
//        }
//    }