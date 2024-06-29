package com.nicolasMorales.InventariumSystem.mapper;

import com.nicolasMorales.InventariumSystem.dto.ProductDTO;
import com.nicolasMorales.InventariumSystem.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * @author Nicolas Morales.
 * Metodos Mapper para mapear Productos.
 */
@Component
public class ProductsMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Product productDTOaProduct (ProductDTO productDTO) {
        Product producto = modelMapper.map(productDTO, Product.class);
        return producto;
    }

    public ProductDTO productaProductDTO (Product product) {
        ProductDTO producto = modelMapper.map(product, ProductDTO.class);
        return producto;
    }
}
