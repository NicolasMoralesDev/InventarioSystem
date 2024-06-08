package com.nicolasMorales.ProductService.repository;

import com.nicolasMorales.ProductService.dto.ProductDowndloadPdfDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

@FeignClient(name = "reporting-service/api/v1/pdf")
public interface IReportingClient {

    /**
     * Metodo para registrar masivo de nuevos productos mediante el servidor de Reportes.
     * @param productos Enviamos un Array de Products al servidor.
     * @return Devuelve el estado de la operacion y el PDF generado.
     */
    @PostMapping("/products/generate")
    public HashMap<String, String> generatePDF(@RequestBody List<ProductDowndloadPdfDTO> productos);
}
