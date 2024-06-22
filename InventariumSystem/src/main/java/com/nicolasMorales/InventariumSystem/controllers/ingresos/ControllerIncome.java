package com.nicolasMorales.InventariumSystem.controllers.ingresos;


import com.nicolasMorales.InventariumSystem.dto.IncomeDTO;
import com.nicolasMorales.InventariumSystem.dto.IncomeEditDTO;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.services.impl.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author Nicolas Morales.
 * Clase controller para los egresos.
 */
@RestController
@RequestMapping("/api/v1/income")
@PreAuthorize("denyAll()")
@CrossOrigin(origins = "*")
public class ControllerIncome {

    @Autowired
    private IncomeService incomeService;

    /**
     * Controllador para registrar ingresos.
     * @param income Recibe los datos a registrar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje.
     */
    @PostMapping(value = "/register")
    @PreAuthorize("hasAuthority('INGRESO')")
    public ResponseEntity<?> registerIncome(@RequestBody IncomeDTO income){
        HashMap<String, String> response = new HashMap<>();

        try {
            incomeService.createIncome(income);
            response.put("msg", "Ingreso registrado correctamente!");
            return ResponseEntity.ok().body(response);

        } catch (Exception e){
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body("Error "+ response);
        }

    }

    /**
     * Controllador para obtener todos los registros de ingresos.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un listado de Registro de Ingresos.
     */
    @GetMapping(value = "/getAll")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<?> getIncome(){
        try {
            return  ResponseEntity.ok().body(incomeService.getAllIncome());
        } catch (Exception e){
            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }
    }

    /**
     * Controllador para obtener un registro por su ID.
     * @param id Recibe el ID del registro solicitado.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y el registro (si es que se ecuentra).
     */
    @GetMapping(value = "/get/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<?> getIncomeById(@PathVariable UUID id){

        try {

            return  ResponseEntity.ok().body(incomeService.getIncomeById(id));

        } catch (Exception e){

            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }

    }

    /**
     * Controllador para borrar un registro de ingreso por su ID.
     * @param id Recibe el ID del registro a borrar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje.
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('MODIFICACION')")
    public ResponseEntity<?> deleteById(@PathVariable UUID id){

        HashMap<String, String> response = new HashMap<>();
        try {

            String status = incomeService.deleteByIdIncome(id);

            if (status == "Registro Borrado Correctamente!") {

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

    /**
     * Controllador para editar registros de ingresos.
     * @param edit Recibe el registro a modificar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje.
     */
    @PutMapping(value = "/modify")
    @PreAuthorize("hasAuthority('MODIFICACION')")
    public ResponseEntity<?> editIncome(@RequestBody IncomeEditDTO edit){
        HashMap<String, String> response = new HashMap<>();

        try {
            incomeService.editIncome(edit);
            response.put("msg", "Registro editado con Exito!!");
            return ResponseEntity.ok().body(response);
        } catch (BussinesException e){
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body(response);
        }
    }
}
