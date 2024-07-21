package com.nicolasMorales.InventariumSystem.controllers.categorias;

import com.nicolasMorales.InventariumSystem.entity.Category;
import com.nicolasMorales.InventariumSystem.services.ICategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Controladores de categorias.
 */
@RestController
@RequestMapping("/api/v1/category")
@CrossOrigin(origins = "*")
public class ControllerCategory {

    private static Logger logger = LoggerFactory.getLogger(ControllerCategory.class);

    @Autowired
    private ICategoryService categoryServ;

    /**
     * Controllador para crear una categoria.
     * @param category Recibe la categoria a crear.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @PostMapping(value = "/create")
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        HashMap<String, String> response = new HashMap<>();
        try {

            categoryServ.createCategory(category);
            response.put("msg", "Categoria agregada!!");
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            logger.error(e.getMessage());
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body("Error "+ response);
        }
    }

    /**
     * Controllador para obtener todas las categorias.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @GetMapping(value = "/getAll")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getCategory(){
        try {
            return  ResponseEntity.ok().body(categoryServ.getCategorys());
        } catch (Exception e){
            logger.error(e.getMessage());
            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }
    }

    /**
     * Controllador para obtener una categoria por ID.
     * @param id Recibe el id de la categoria a obtener.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y la categoria (si es que existe).
     */
    @GetMapping(value = "/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<?> getCategoryById(@PathVariable UUID id){
        try {
            return  ResponseEntity.ok().body(categoryServ.getCategorysById(id));
        } catch (Exception e){
            logger.error(e.getMessage());
            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }
    }

    /**
     * Controllador para borrar una categoria.
     * @param id Recibe el id de la categoria a borrar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje de la operacion.
     */
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id){
        HashMap<String, String> response = new HashMap<>();
        try {

            categoryServ.deleteCategory(id);
            response.put("msg", "Producto Borrado Correctamente!!");
            return  ResponseEntity.ok().body(response);
        } catch (Exception e){
            logger.error(e.getMessage());
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body("Error "+response);
        }
    }

    /**
     * Controllador para editar una categoria.
     * @param edit Recibe el id de la categoria a obtener.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje de la operacion.
     */
    @PutMapping(value = "/put")
    public ResponseEntity<?> editCategory(@RequestBody Category edit){
        HashMap<String, String> response = new HashMap<>();

        try {
            categoryServ.modifyCategory(edit);
            response.put("msg", "Categoria Modificada!");
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            logger.error(e.getMessage());
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}
