package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.dto.ProductDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de reportes PDF.
 */
public interface IPdfService {

     /**
      * Servicio para generar reportes PDF de Productos seleccionados.
      * @param productos Recibe un Array de ProductDTO con los productos.
      * @return Retorna el ByteArrayOutputStream del PDF generado.
      * @throws IOException Excepcion para manejar errores de la generacion del PDF.
      */
     ByteArrayOutputStream generatePdfProductos (List<ProductDTO> productos) throws IOException;
}
