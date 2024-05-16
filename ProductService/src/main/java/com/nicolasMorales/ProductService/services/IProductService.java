package com.nicolasMorales.ProductService.services;


import com.nicolasMorales.ProductService.dto.ProductDTO;
import com.nicolasMorales.ProductService.exepciones.BussinesException;
import com.nicolasMorales.ProductService.models.Product;

import java.util.List;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de Producto.
 */
public interface IProductService{


      List<Long> createBulkProducts(List<Product> products);

      String deleteProduct(UUID id);

      String deleteProducts(List <UUID> ids);

      List <Product> getProducts();

      Product getProductsById(UUID id);

      String modifyProduct(Product nuevo);

      ProductDTO getProductsByCode(long code) throws BussinesException;
}
