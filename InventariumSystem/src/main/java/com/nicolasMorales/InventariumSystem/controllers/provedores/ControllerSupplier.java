package com.nicolasMorales.InventariumSystem.controllers.provedores;


import com.nicolasMorales.InventariumSystem.entity.Supplier;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.services.ISupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * @author Nicolas Morales.
 * Controladores para los provedores.
 */
@RestController
@RequestMapping("/api/v1/supplier")
@CrossOrigin(origins = "*")
public class ControllerSupplier {

    private static Logger logger = LoggerFactory.getLogger(ControllerSupplier.class);

    @Autowired
    private ISupplierService supplierServ;

    /**
     * Controllador para obtener todos los provedores registrados.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y una lista de provedores.
     */
    @GetMapping(value = "/getAll")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<?> getAllSuppliers(){
        HashMap<String, String> response = new HashMap<>();

        try {
            List<Supplier> suppliers = supplierServ.getSuppliers();
            return  ResponseEntity.ok().body(suppliers);
        } catch (RuntimeException | BussinesException e){
            logger.error(e.getMessage());
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Controllador para registrar provedores.
     * @param nuevo Recibe los el provedor a registrar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje.
     */
    @PostMapping(value = "/create")
    public ResponseEntity<?> createSupplier(@RequestBody Supplier nuevo){
        HashMap<String, String> response = new HashMap<>();

        try {
            supplierServ.createSupplier(nuevo);
            response.put("msg", "Provedor cargado Exitosamente!");
            return  ResponseEntity.ok().body(response);
        } catch (BussinesException  e) {
            logger.error(e.getMessage());
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Controllador para borrar un provedor por su id.
     * @param id Recibe un UUID del provedor a borrar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAuthority('MODIFICACION')")
    public ResponseEntity<?> deleteSupplier(@PathVariable UUID id){
        HashMap<String, String> response = new HashMap<>();

        try {
            String status = supplierServ.deleteSupplier(id);
            if (status == "Proveedor Borrado!") {
                response.put("msg", status);
                return  ResponseEntity.ok().body(response);
            } else {
                response.put("error", status);
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e){
            logger.error(e.getMessage());
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Controllador para borrar multiples provedores por su id.
     * @param ids Recibe los UUID de los provedores a borrar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @PostMapping(value = "/delete/bulk")
    @PreAuthorize("hasAuthority('MODIFICACION')")
    public ResponseEntity<?> deleteSuppliers(@RequestBody List<UUID> ids){
        HashMap<String, String> response = new HashMap<>();

        try {
            supplierServ.deleteSuppliers(ids);
            response.put("msg", "Provedores borrados correctamente");
            return  ResponseEntity.ok().body(response);
        } catch (Exception e){
            logger.error(e.getMessage());
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Controllador para editar un provedor.
     * @param modify Recibe el provedor a editar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensage de la operacion.
     */
    @PutMapping(value = "/edit")
    @PreAuthorize("hasAuthority('MODIFICACION')")
    public ResponseEntity<?> editSupplier(@RequestBody Supplier modify){
        HashMap<String, String> response = new HashMap<>();

        try {
            supplierServ.modifySupplier(modify);
            response.put("msg", "Proveedor modificado correctamente!!");
            return  ResponseEntity.ok().body(response);
        } catch (RuntimeException | BussinesException e){
            logger.error(e.getMessage());
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }

}
