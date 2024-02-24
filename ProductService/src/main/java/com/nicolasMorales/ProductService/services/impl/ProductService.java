package com.nicolasMorales.ProductService.services.impl;


import com.nicolasMorales.ProductService.models.Product;
import com.nicolasMorales.ProductService.repository.IProductRepository;
import com.nicolasMorales.ProductService.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements IProductService {


    @Autowired
    private IProductRepository productRepo;

    @Override
    public String createProduct(Product nuevo) {

        try {

            if (productRepo.findByName(nuevo.getName()) == null) {

                productRepo.save(nuevo);
                return "Producto agregado!!";

            } else {
                return "Este Producto ya Existe!!";
            }

        } catch (Exception e){
            return  "Error "+ e;
        }
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
}
