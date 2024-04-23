package com.nicolasMorales.ProductService.services;


import com.nicolasMorales.ProductService.dto.ProductDTO;
import com.nicolasMorales.ProductService.models.Product;

import java.util.List;
import java.util.UUID;


public interface IProductService{


      List<Long> createBulkProducts(List<Product> products);

      String deleteProduct(UUID id);

      List<Product> getProducts();

      Product getProductsById(UUID id);

      String modifyProduct(Product nuevo);

      ProductDTO getProductsByCode(long code);
}
