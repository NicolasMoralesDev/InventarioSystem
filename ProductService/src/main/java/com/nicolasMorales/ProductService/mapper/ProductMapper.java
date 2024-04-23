package com.nicolasMorales.ProductService.mapper;

import com.nicolasMorales.ProductService.dto.ProductDTO;
import com.nicolasMorales.ProductService.models.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductDTO productToProductDTO (Product product){

        ProductDTO nuevo = new ProductDTO();
        nuevo.setId(product.getId());
        nuevo.setImg(product.getImg());
        nuevo.setName(product.getName());
        nuevo.setMarca(product.getMarca());
        nuevo.setCant(product.getCant());
        nuevo.setSubCategoria(product.getSubCategoria());
        nuevo.setCodigo(product.getCodigo());
        nuevo.setPrecio(product.getPrecio());

        return nuevo;
    }
}
