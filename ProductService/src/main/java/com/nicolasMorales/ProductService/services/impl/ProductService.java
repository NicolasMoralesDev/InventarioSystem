package com.nicolasMorales.ProductService.services.impl;


import com.nicolasMorales.ProductService.dto.ProductDTO;
import com.nicolasMorales.ProductService.mapper.ProductMapper;
import com.nicolasMorales.ProductService.models.Product;
import com.nicolasMorales.ProductService.repository.IProductRepository;
import com.nicolasMorales.ProductService.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepo;

    @Autowired
    private ProductMapper productMapper;


    public void createProduct(Product nuevo) {

        Product producto = productRepo.findByCodigo(nuevo.getCodigo());

            if ( producto == null) {
                productRepo.save(nuevo);

            } else if (producto.getCodigo() == nuevo.getCodigo()){
                producto.setCant(nuevo.getCant()+producto.getCant());
                productRepo.save(producto);
            }
    }

    @Override
    public List<Long> createBulkProducts(List<Product> products) {

           List<Long> listProducts = new ArrayList<>();

           for (Product product : products ){

               this.createProduct(product);
               listProducts.add(product.getCodigo());
           }

           return listProducts;

    }

    @Override
    public String deleteProduct(UUID id) {

        try {
            productRepo.deleteById(id);

            return "Producto Borrado Correctamente!!";
        } catch (Exception e){
            return  "Error "+ e;
        }
    }

    @Override
    public List<Product> getProducts() {

          return  productRepo.findAll();

    }

    @Override
    public Product getProductsById(UUID id) {

        return productRepo.findById(id).orElse(null);
    }

    @Override
    public String modifyProduct(Product nuevo) {

        try {

            productRepo.save(nuevo);
            return "Producto Modificado!";
        } catch (Exception e){
            return "Error "+ e.getMessage();
        }
    }

    @Override
    public ProductDTO getProductsByCode(long code) {

        return productMapper.productToProductDTO(productRepo.findByCodigo(code));

    }
}
