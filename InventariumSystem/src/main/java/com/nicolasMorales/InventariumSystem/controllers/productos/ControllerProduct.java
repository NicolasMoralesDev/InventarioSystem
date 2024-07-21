package com.nicolasMorales.InventariumSystem.controllers.productos;

import com.nicolasMorales.InventariumSystem.entity.Product;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.services.impl.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Controladores de productos.
 */
@RestController
@RequestMapping("/api/v1/product")
@PreAuthorize("denyAll()")
@CrossOrigin(origins = "*")
public class ControllerProduct {

    private static Logger logger = LoggerFactory.getLogger(ControllerProduct.class);

    @Autowired
    private ProductService productServ;

    /**
     * Controllador para registrar masivaente productos.
     * @param product Recibe una lista con los nuevos productos.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje.
     */
    @PostMapping(value = "/bulk")
    @PreAuthorize("hasAuthority('INGRESO')")
    public ResponseEntity<?> addBulkProducts(@RequestBody List<Product> product) {
        HashMap<String, String> response = new HashMap<>();
        try {
            List<Long> listProducts = productServ.createBulkProducts(product);
            return ResponseEntity.ok().body(listProducts);
        } catch (BussinesException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(response.put("error", e.getMessage()));
        }
    }

    /**
     * Controllador para registrar productos individualmente.
     * @param product Recibe el nuevo producto.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje.
     */
    @PostMapping(value = "/post")
    @PreAuthorize("hasAuthority('INGRESO')")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        HashMap<String, String> response = new HashMap<>();
        try {
            productServ.createProduct(product);
            response.put("msg", "Producto cargado Correctamente!!");
            return ResponseEntity.ok().body(response);
        } catch (BussinesException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(response.put("error", e.getMessage()));
        }
    }

    /**
     * Controllador para obtener todos los productos registrados.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y una lista de productos.
     */
    @GetMapping(value = "/getAll")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getProduct() {
        try {
            return ResponseEntity.ok().body(productServ.getProducts());
        } catch (BussinesException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    /**
     * Controllador para obtener un producto por su id.
     *
     * @param id Recibe un UUID del producto solicitado.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y el producto (si es que se encuentra).
     */
    @GetMapping(value = "/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<?> getProductById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok().body(productServ.getProductsById(id));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    /**
     * Controllador para obtener un producto por su codigo de Barras.
     * @param code Recibe un UUID del producto solicitado.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y el producto (si es que se encuenta).
     */
    @GetMapping(value = "/get/code/{code}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getProductByCode(@PathVariable Long code) {
        HashMap<String, String> response = new HashMap<>();
        try {
            return ResponseEntity.ok().body(productServ.getProductsByCode(code));
        } catch (BussinesException e) {
            logger.error(e.getMessage());
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Controllador para ser consumido por IncomeService para obtener un producto por su codigo de Barras.
     * @param code Recibe un UUID del producto solicitado.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y el producto (si es que se encuenta).
     */
//    @GetMapping(value = "/get/income/code/{code}")
//    public ResponseEntity<?> getProductByCodeForIncome(@PathVariable Long code) {
//        HashMap<String, String> response = new HashMap<>();
//        ProductIncomeResponseDTO productResponse = new ProductIncomeResponseDTO();
//
//        try {
//            ProductDTO producto = productServ.getProductsByCode(code);
//            productResponse.setNombre(producto.getNombre());
//            productResponse.setCodigo(producto.getCodigo());
//            return ResponseEntity.ok().body(productResponse);
//        } catch (BussinesException e) {
//            response.put("error", e.getMessage());
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

    /**
     * Controllador para borrar un producto por su id.
     * @param id Recibe un UUID del producto a borrar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('MODIFICACION')")
    public ResponseEntity<?> deleteById(@PathVariable UUID id) {
        HashMap<String, String> response = new HashMap<>();
        try {
            productServ.deleteProduct(id);
            response.put("msg", "Producto borrado Correctamente!!");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.put("Error ", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Controllador para borrado masivo de productos por su id.
     * @param ids Recibe una Lista de UUID con los ids de los  producto solicitado.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @PostMapping(value = "/delete/bulk")
    @PreAuthorize("hasAuthority('MODIFICACION')")
    public ResponseEntity<?> deleteProductsById(@RequestBody List<UUID> ids) {
        HashMap<String, String> response = new HashMap<>();
        try {
            productServ.deleteProducts(ids);
            response.put("msg", "Productos borrados Correctamente!!");
            return ResponseEntity.ok().body(response);
        } catch (BussinesException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error " + e.getMessage());
        }
    }

    /**
     * Controllador para editar un producto.
     * @param edit Recibe el producto a editar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @PutMapping(value = "/put")
    @PreAuthorize("hasAuthority('MODIFICACION')")
    public ResponseEntity<?> editProduct(@RequestBody Product edit) {
        HashMap<String, String> response = new HashMap<>();

        try {
            productServ.modifyProduct(edit);
            response.put("msg", "Producto modificado con exito!");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Controllador para generar PDF.
     * @param productosIds Recibe un Array de ids.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado, un mensaje y el PDF generado.
     */
    @PostMapping(value = "/generate/pdf")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> downloadPDF(@RequestBody List<UUID> productosIds) throws BussinesException, IOException {
        try {
            Map<String, String> response = productServ.downloadPDF(productosIds);
            return ResponseEntity.ok().body(response);
        } catch (BussinesException e) {
            logger.error(e.getMessage());
            throw new BussinesException("Error " + e.getMessage());
        }
    }
}