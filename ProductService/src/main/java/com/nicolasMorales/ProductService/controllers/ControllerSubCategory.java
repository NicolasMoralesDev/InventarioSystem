package com.nicolasMorales.ProductService.controllers;

import com.nicolasMorales.ProductService.exceptions.BussinesException;
import com.nicolasMorales.ProductService.models.SubCategory;
import com.nicolasMorales.ProductService.services.impl.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 *  @author Nicolas Morales.
 *  Controller de Sub Categorias.
 */
@RestController
@RequestMapping("/api/v1/subCategory")
@CrossOrigin(origins = "*")
public class ControllerSubCategory {

    @Autowired
    private SubCategoryService subCateServ;

    /**
     * Controllador para crear sub Categorias.
     * @param subCate Recibe la nueva sub Categoria.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje.
     */
    @PostMapping(value = "/create")
    public ResponseEntity<?> addSubCategory(@RequestBody SubCategory subCate){
        HashMap<String, String> response = new HashMap<>();

        try {
            subCateServ.createSubCategory(subCate);
            response.put("msg", "Sub Categoria creada con Exito!");
            return  ResponseEntity.ok().body(response);
        } catch (BussinesException e) {
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Controllador para obtener todas las sub Categorias.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un listado de sub Categorias (si es que se encuentran).
     */
    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getAll(){
        HashMap<String, String> response = new HashMap<>();

        try {
            return  ResponseEntity.ok().body(subCateServ.getAllSubCategory());
        } catch (BussinesException e){
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Controllador para editar una sub Categoria.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un listado de sub Categorias (si es que se encuentran).
     */
    @PutMapping(value = "/modify")
    public ResponseEntity<?> modifySubCategoria(@RequestBody SubCategory edit){
        HashMap<String, String> response = new HashMap<>();

        try {
            subCateServ.editSubCategory(edit);
            response.put("msg","Sub Categoria editada con Exito!");
            return  ResponseEntity.ok().body(response);
        } catch (BussinesException e){
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }
}
