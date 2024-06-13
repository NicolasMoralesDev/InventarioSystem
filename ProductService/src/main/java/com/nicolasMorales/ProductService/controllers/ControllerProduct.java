package com.nicolasMorales.ProductService.controllers;

import com.nicolasMorales.ProductService.dto.ProductDTO;
import com.nicolasMorales.ProductService.dto.ProductIncomeResponseDTO;
import com.nicolasMorales.ProductService.exceptions.BussinesException;
import com.nicolasMorales.ProductService.models.Product;
import com.nicolasMorales.ProductService.services.impl.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
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
       try {
           List <Long> listProducts = productServ.createBulkProducts(product);
           return  ResponseEntity.ok().body(listProducts);
       } catch (BussinesException e) {
           return  ResponseEntity.badRequest().body(response.put("error", e.getMessage()));
       }
    }

    /**
     * Controllador para crear productos individualmente.
     * @param product Recibe el nuevo producto.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje.
     */
    @PostMapping(value = "/post")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
         HashMap<String, String> response = new HashMap<>();
         try {
             productServ.createProduct(product);
             response.put("msg", "Producto cargado Correctamente!!");
             return  ResponseEntity.ok().body(response);
         } catch (BussinesException e){
             return  ResponseEntity.badRequest().body(response.put("error", e.getMessage()));
         }
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
        } catch (BussinesException e){
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
    public ResponseEntity<?> getProductByCode(@PathVariable Long code){
        HashMap<String, String> response = new HashMap<>();
        try {
            return  ResponseEntity.ok().body(productServ.getProductsByCode(code));
        } catch (BussinesException e){
            response.put("error",e.getMessage());
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Controllador para ser consumido por IncomeService para obtener un producto por su codigo de Barras.
     * @param code Recibe un UUID del producto solicitado.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y el producto (si es que se encuenta).
     */
    @GetMapping(value = "/get/income/code/{code}")
    public ResponseEntity<?> getProductByCodeForIncome(@PathVariable Long code){
        HashMap<String, String> response = new HashMap<>();
        ProductIncomeResponseDTO productResponse = new ProductIncomeResponseDTO();

        try {
            ProductDTO producto = productServ.getProductsByCode(code);
            productResponse.setNombre(producto.getNombre());
            productResponse.setCodigo(producto.getCodigo());
            return  ResponseEntity.ok().body(productResponse);
        } catch (BussinesException e){
            response.put("error",e.getMessage());
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Controllador para borrar un producto por su id.
     * @param id Recibe un UUID del producto a borrar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID id){
        HashMap<String, String> response = new HashMap<>();
        try {
            productServ.deleteProduct(id);
            response.put("msg", "Producto borrado Correctamente!!");
            return  ResponseEntity.ok().body(response);
        } catch (Exception e){
            response.put("Error ", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Controllador para borrado masivo de productos por su id.
     * @param ids Recibe una Lista de UUID con los ids de los  producto solicitado.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @PostMapping(value = "/delete/bulk")
    public ResponseEntity<?> deleteProductsById(@RequestBody List<UUID> ids){
        HashMap<String, String> response = new HashMap<>();
        try {
            productServ.deleteProducts(ids);
            response.put("msg", "Productos borrados Correctamente!!");
            return  ResponseEntity.ok().body(response);
        } catch (BussinesException e){
            return  ResponseEntity.badRequest().body("Error "+e.getMessage());
        }
    }

    /**
     * Controllador para editar un producto.
     * @param edit Recibe el Producto a editar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @PutMapping(value = "/put")
    public ResponseEntity<?> editProduct(@RequestBody Product edit){
            HashMap<String, String> response = new HashMap<>();

        try {
            productServ.modifyProduct(edit);
            response.put("msg", "Producto modificado con exito!");
            return ResponseEntity.ok().body(response);
        } catch (Exception e){
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Controllador para generar PDF.
     * @param productosIds Recibe un Array de ids.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado, un mensaje y el PDF generado.
     */
    @PostMapping(value = "/generate/pdf")
    public ResponseEntity<?> downloadPDF(@RequestBody List <UUID> productosIds) throws BussinesException, IOException {
        try {
            HashMap<String, String> response =  productServ.downloadPDF(productosIds);
            System.out.println(response.get("url"));
            return ResponseEntity.ok().body(response);
        } catch (BussinesException e){
            throw new BussinesException("Error "+ e.getMessage());
        }
    }
}
