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

/**
 *  @author Nicolas Morales.
 *  Controller de Productos.                            
 */
@RestController
@RequestMapping("/api/v1/product")
@CrossOrigin(origins = "*")
public class ControllerProduct {

    @Autowired
    private ProductService productServ;


//    @Value("${server.port}")
//    private int serverPort;

   /**
    * Controllador para crear masivaente productos.
    * @param product Recibe una lista con los nuevos  productos.
    * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje.
    */
    @PostMapping(value = "/bulk")
    public ResponseEntity<?> addBulkProducts(@RequestBody List<Product> product){

        HashMap<String, String> response = new HashMap<>();
        List <Long> listProducts = productServ.createBulkProducts(product);
        return  ResponseEntity.ok().body(listProducts);
    }

    /**
     * Controllador para obtener productos paginados.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y una lista de productos.
     */
    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getProduct(){
//        System.out.println("serverPort = " + serverPort);
        try {
            return  ResponseEntity.ok().body(productServ.getProducts());

        } catch (Exception e){

            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }
    }

    /**
     * Controllador para obtener un producto por su id.
     * @param id Recibe un UUID del producto solicitado.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y el producto (si es que se encuentra).
     */
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<?> getProductById(@PathVariable UUID id){
        try {
            return  ResponseEntity.ok().body(productServ.getProductsById(id));

        } catch (Exception e){
            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }
    }

    /**
     * Controllador para obtener un producto por su codigo de Barras.
     * @param code Recibe un UUID del producto solicitado.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y el producto (si es que se encuenta).
     */
    @GetMapping(value = "/get/code/{code}")
    public ResponseEntity<?> getProductByCode(@PathVariable long code){
        try {
            return  ResponseEntity.ok().body(productServ.getProductsByCode(code));

        } catch (Exception e){

            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }
    }

    /**
     * Controllador para borrar un producto por su id.
     * @param id Recibe un UUID del producto a borrar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @DeleteMapping(value = "/delete/{id}")
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

    /**
     * Controllador para borrado masivo de productos por su id.
     * @param ids Recibe una Lista de UUID con los ids de los  producto solicitado.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @PostMapping(value = "/delete/bulk")
    public ResponseEntity<?> deleteProductsById(@RequestBody List<UUID> ids){
        System.out.println("ids = " + ids);
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

    /**
     * Controllador para editar un producto.
     * @param edit Recibe el Producto a editar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @PutMapping(value = "/put")
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
