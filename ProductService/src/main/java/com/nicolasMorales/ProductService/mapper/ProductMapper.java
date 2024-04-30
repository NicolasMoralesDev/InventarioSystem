package com.nicolasMorales.ProductService.mapper;

import com.nicolasMorales.ProductService.dto.ProductDTO;
import com.nicolasMorales.ProductService.models.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Nicolas Morales
 * Clase mapper para entidad Producto y sus DTOS
 */
@Component
public class ProductMapper {

    public ProductDTO productToProductDto(Product product) {
        ProductDTO productDto = new ProductDTO();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrecio(product.getPrecio());
        productDto.setImg(product.getImg());
        productDto.setCodigo(product.getCodigo());
        productDto.setMarca(product.getMarca());
        productDto.setBorrado(product.isBorrado());
        productDto.setCant(product.getCant());
        productDto.setSubCategoria(product.getSubCategoria());
        productDto.setCategoria(product.getCategoria());
        productDto.setDescripcion(productDto.getDescripcion());

        return productDto;
    }

    public ProductDTO productToProductDTO (Product product){
        ProductDTO nuevo = new ProductDTO();
        nuevo.setId(product.getId());
        nuevo.setImg(product.getImg());
        nuevo.setName(product.getName());
        nuevo.setMarca(product.getMarca());
        nuevo.setCant(product.getCant());
        nuevo.setSubCategoria(product.getSubCategoria());
        nuevo.setCategoria(product.getCategoria());
        nuevo.setCodigo(product.getCodigo());
        nuevo.setPrecio(product.getPrecio());
        nuevo.setDescripcion(product.getDescripcion());

        return nuevo;
    }

    public List<ProductDTO> productListToProductDtoList(List<Product> listProduct){
        List<ProductDTO> listProductDto = new ArrayList<>();
        for(Product p : listProduct){
            listProductDto.add(this.productToProductDto(p));
        }
        return listProductDto;
    }
}
