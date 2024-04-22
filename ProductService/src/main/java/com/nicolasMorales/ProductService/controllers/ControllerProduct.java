package com.nicolasMorales.ProductService.controllers;


import com.nicolasMorales.ProductService.models.Category;
import com.nicolasMorales.ProductService.models.Product;
import com.nicolasMorales.ProductService.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "*")
public class ControllerProduct {

    @Autowired
    private ProductService productServ;


    @Value("${server.port}")
    private int serverPort;

    @PostMapping(value = "/bulk")
    public ResponseEntity<List> addBulkProducts(@RequestBody List<Product> product){

        HashMap<String, String> response = new HashMap<>();

            List <Integer> listProducts = productServ.createBulkProducts(product);

            return  ResponseEntity.ok().body(listProducts);


    }
    @GetMapping("/get")
    public ResponseEntity<?> getProduct(){

        System.out.println("serverPort = " + serverPort);
        try {

                return  ResponseEntity.ok().body(productServ.getProducts());

        } catch (Exception e){

            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getProductById(@PathVariable UUID id){

        try {

            return  ResponseEntity.ok().body(productServ.getProductsById(id));

        } catch (Exception e){

            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id){

        try {

            HashMap<String, String> response = new HashMap<>();

            String status = productServ.deleteProduct(id);

            if (status == "Producto Borrado Correctamente!!") {

                response.put("msg", status );

                return  ResponseEntity.ok().body(response);


            } else {

                response.put("error", status);
                return  ResponseEntity.badRequest().body(response);

            }

        } catch (Exception e){

            return  ResponseEntity.badRequest().body("Error "+e.getMessage());

        }

    }

    @PutMapping("/put")
    public  ResponseEntity<?> editProduct(@RequestBody Product edit){

        HashMap<String, String> response = new HashMap<>();

        try {

            String status = productServ.modifyProduct(edit);

            if (status == "Producto Modificado!") {

                response.put("msg", status);
                return ResponseEntity.ok().body(response);
            } else {

                response.put("error", status);
                return ResponseEntity.ok().body(response);
            }

        } catch (Exception e){
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }
}
