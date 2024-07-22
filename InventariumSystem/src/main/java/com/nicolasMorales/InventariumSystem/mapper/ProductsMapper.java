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

    /**
     * Metodo para Mappear de ProductDTO a Product.
     * @param productDTO Recibe un productDTO a mappear.
     * @return Devuelve un Product.
     */
    public Product productDTOaProduct (ProductDTO productDTO) {
        Product producto = modelMapper.map(productDTO, Product.class);
        return producto;
    }

    /**
     * Metodo para Mappear de Product a ProductDTO.
     * @param product Recibe un Product a mappear.
     * @return Devuelve un ProductDTO.
     */
    public ProductDTO productaProductDTO (Product product) {
        ProductDTO producto = modelMapper.map(product, ProductDTO.class);
        return producto;
    }
}
