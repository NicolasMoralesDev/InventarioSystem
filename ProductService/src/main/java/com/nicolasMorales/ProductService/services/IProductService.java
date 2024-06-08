package com.nicolasMorales.ProductService.services;


import com.nicolasMorales.ProductService.dto.ProductDTO;
import com.nicolasMorales.ProductService.exceptions.BussinesException;
import com.nicolasMorales.ProductService.models.Product;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de Producto.
 */
public interface IProductService{

      void createProduct(Product nuevo) throws BussinesException;

      List<Long> createBulkProducts(List<Product> products) throws BussinesException;

      void deleteProduct(UUID id) throws BussinesException;

      void deleteProducts(List <UUID> ids) throws BussinesException;

      List <Product> getProducts() throws BussinesException;

      Product getProductsById(UUID id) throws BussinesException;

      String modifyProduct(Product nuevo);

      ProductDTO getProductsByCode(long code) throws BussinesException;

      HashMap<String, String> downloadPDF(List<UUID> productosIds) throws BussinesException;
}
