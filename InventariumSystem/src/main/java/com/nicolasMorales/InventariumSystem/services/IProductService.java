package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.dto.ProductDTO;
import com.nicolasMorales.InventariumSystem.entity.Product;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de Productos.
 */
public interface IProductService{

    void createProduct(Product nuevo) throws BussinesException;

    List<Long> createBulkProducts(List<Product> products) throws BussinesException;

    void createProductExpense(Product nuevo) throws BussinesException;

    List<Long> createExpenseProducts(List<Product> products) throws BussinesException;

    void deleteProduct(UUID id) throws BussinesException;

    void deleteProducts(List <UUID> ids) throws BussinesException;

    List <Product> getProducts() throws BussinesException;

    Product getProductsById(UUID id) throws BussinesException;

    void modifyProduct(Product nuevo) throws BussinesException;

    ProductDTO getProductsByCode(long code) throws BussinesException;

    Map<String, String> downloadPDF(List<UUID> productosIds) throws BussinesException;
}
