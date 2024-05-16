package com.nicolasMorales.ProductService.mapper;

import com.nicolasMorales.ProductService.dto.ProductDTO;
import com.nicolasMorales.ProductService.models.Product;
import org.springframework.stereotype.Component;

/**
 * @author Nicolas Morales
 * Clase mapper para entidad Producto y sus DTOS
 */
@Component
public class ProductMapper {

    public ProductDTO productToProductDTO (Product product){
        ProductDTO nuevo = new ProductDTO();
        nuevo.setId(product.getId());
        nuevo.setImg(product.getImg());
        nuevo.setNombre(product.getNombre());
        nuevo.setMarca(product.getMarca());
        nuevo.setCant(product.getCant());
        nuevo.setSubCategoria(product.getSubCategoria());
        nuevo.setCategoria(product.getCategoria());
        nuevo.setCodigo(product.getCodigo());
        nuevo.setPrecio(product.getPrecio());
        nuevo.setDescripcion(product.getDescripcion());

        return nuevo;
    }

}
