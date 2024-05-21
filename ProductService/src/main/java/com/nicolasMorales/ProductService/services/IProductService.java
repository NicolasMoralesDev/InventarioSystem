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

      void createProduct(Product nuevo) throws BussinesException;

      List<Long> createBulkProducts(List<Product> products) throws BussinesException;

      String deleteProduct(UUID id);

      void deleteProducts(List <UUID> ids) throws BussinesException;

      List <Product> getProducts() throws BussinesException;

      Product getProductsById(UUID id);

      String modifyProduct(Product nuevo);

      ProductDTO getProductsByCode(long code) throws BussinesException;
}
