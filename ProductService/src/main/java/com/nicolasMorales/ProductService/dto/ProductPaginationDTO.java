package com.nicolasMorales.ProductService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  @author Nicolas Morales
 *  DTO para la paginacion
 *  de productos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPaginationDTO {

    private List<ProductDTO> productos;
    private int page;
    private int total;
}
