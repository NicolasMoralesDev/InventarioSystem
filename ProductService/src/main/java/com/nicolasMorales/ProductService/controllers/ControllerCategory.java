package com.nicolasMorales.ProductService.controllers;


import com.nicolasMorales.ProductService.models.Category;
import com.nicolasMorales.ProductService.services.impl.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "*")
public class ControllerCategory {


    @Autowired
    private CategoryService categoryServ;


    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@RequestBody Category category){

        HashMap<String, String> response = new HashMap<>();
        try {

            String status = categoryServ.createCategory(category);

            if (status == "Categoria agregada!!") {

                response.put("msg", status );

                return ResponseEntity.ok().body(response);


            } else if (status == "Esta categoria ya Existe!"){

                response.put("error", status);
                return  ResponseEntity.badRequest().body(response);

            } else {

                response.put("error", status);
                return  ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e){
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body("Error "+ response);
        }

    }

    @GetMapping("/get")
    public ResponseEntity<?> getCategory(){

        try {

            return  ResponseEntity.ok().body(categoryServ.getCategorys());

        } catch (Exception e){

            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable UUID id){

        try {

            return  ResponseEntity.ok().body(categoryServ.getCategorysById(id));

        } catch (Exception e){

            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id){

        HashMap<String, String> response = new HashMap<>();
        try {

            String status = categoryServ.deleteCategory(id);

            if (status == "Producto Borrado Correctamente!!") {

                response.put("msg", status );

                return  ResponseEntity.ok().body(response);


            } else {

                response.put("error", status);
                return  ResponseEntity.badRequest().body(response);

            }

        } catch (Exception e){
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body("Error "+response);

        }

    }


    @PutMapping("/put")
    public  ResponseEntity<?> editCategory(@RequestBody Category edit){

        HashMap<String, String> response = new HashMap<>();

        try {

            String status = categoryServ.modifyCategory(edit);

            if (status == "Categoria Modificada!") {

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
