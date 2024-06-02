
package com.nicolasMorales.IncomeService.repository;
import com.nicolasMorales.IncomeService.dtos.ProductDTO;
import com.nicolasMorales.IncomeService.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * @author Nico Morales.
 * Interfaz cliente para consumir el servicio de Productos.
 */
@FeignClient(name = "product-service/api/v1/product")
public interface IProductClient {

    /**
     * Metodo para registrar masivo de nuevos productos mediante el servidor de Productos.
     * @param productos Enviamos un Array de Products al servidor.
     * @return Devuelve un mensaje con el estado de la operacion.
     */
    @PostMapping("/bulk")
    public List<Long> addProducts (@RequestBody List<ProductDTO> productos);

    /**
     * Metodo para obtener un prodycto por su codigo de barras mediante el servidor de Productos.
     * @param codigo Enviamos el codigo de barras del producto a solicitar.
     * @return Devuelve el producto solicitado (si es que existe).
     */
    @GetMapping("/get/income/code/{codigo}")
    public ProductDTO getProductByCode (@PathVariable Long codigo );
    
}
