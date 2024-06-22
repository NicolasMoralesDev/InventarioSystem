package com.nicolasMorales.InventariumSystem.controllers.provedores;


import com.nicolasMorales.InventariumSystem.entity.Supplier;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.services.impl.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author Nicolas Morales.
 * Clase controller para los Provedores.
 */
@RestController
@RequestMapping("/api/v1/supplier")
@CrossOrigin(origins = "*")
public class SupplierController {

    @Autowired
    private SupplierService supplierServ;

    @GetMapping(value = "/getAll")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<?> getAllSuppliers(){
        HashMap<String, String> response = new HashMap<>();

        try {
            List<Supplier> suppliers = supplierServ.getSuppliers();
            return  ResponseEntity.ok().body(suppliers);

        } catch (RuntimeException | BussinesException e){
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createSupplier(@RequestBody Supplier nuevo){
        HashMap<String, String> response = new HashMap<>();

        try {
            supplierServ.createSupplier(nuevo);
            response.put("msg", "Provedor cargado Exitosamente!");
            return  ResponseEntity.ok().body(response);
        } catch (BussinesException  e) {
            response.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

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
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(value = "/edit")
    @PreAuthorize("hasAuthority('MODIFICACION')")
    public ResponseEntity<?> editSupplier(@RequestBody Supplier modify){
        HashMap<String, String> response = new HashMap<>();

        try {
            supplierServ.modifySupplier(modify);
            response.put("msg", "Proveedor modificado correctamente!!");
            return  ResponseEntity.ok().body(response);

        } catch (RuntimeException | BussinesException e){
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }

}
