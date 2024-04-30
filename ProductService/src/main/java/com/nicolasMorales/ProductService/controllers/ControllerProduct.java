package com.nicolasMorales.ProductService.controllers;


import com.nicolasMorales.ProductService.models.Product;
import com.nicolasMorales.ProductService.services.impl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/*
   Controller de Productos
 */
@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "*")
public class ControllerProduct {

    @Autowired
    private ProductService productServ;


    @Value("${server.port}")
    private int serverPort;

    @PostMapping(value = "/bulk")
    public ResponseEntity<?> addBulkProducts(@RequestBody List<Product> product){

        HashMap<String, String> response = new HashMap<>();
        List <Long> listProducts = productServ.createBulkProducts(product);
        return  ResponseEntity.ok().body(listProducts);
    }

    @GetMapping("/getAll/{page}")
    public ResponseEntity<?> getProduct(@PathVariable int page){
        System.out.println("serverPort = " + serverPort);
        try {
            return  ResponseEntity.ok().body(productServ.getProducts(page));

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

    @GetMapping("/get/code/{code}")
    public ResponseEntity<?> getProductByCode(@PathVariable long code){
        try {
            return  ResponseEntity.ok().body(productServ.getProductsByCode(code));

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

    @DeleteMapping("/delete/bulk")
    public ResponseEntity<?> deleteProductsById(@RequestBody List<UUID> ids){
        try {

            HashMap<String, String> response = new HashMap<>();

            String status = productServ.deleteProducts(ids);

            if (status == "Productos Borrados Correctamente!!") {

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
