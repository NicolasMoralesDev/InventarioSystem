package com.nicolasMorales.InventariumSystem.controllers.egresos;

import com.nicolasMorales.InventariumSystem.dto.ExpenseDTO;
import com.nicolasMorales.InventariumSystem.dto.ExpenseDTOResponse;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.services.IExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * @author Nicolas Morales.
 * Controladores para los egresos.
 */
@RestController
@RequestMapping("/api/v1/expense")
@PreAuthorize("denyAll()")
@CrossOrigin(origins = "*")
public class ControllerExpense {

    private static Logger logger = LoggerFactory.getLogger(ControllerExpense.class);

    @Autowired
    private IExpenseService expenseService;

    /**
     * Controllador para registrar egresos.
     * @param expense Recibe los datos a registrar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje.
     */
    @PostMapping(value = "/register")
    @PreAuthorize("hasAuthority('MODIFICACION')")
    public ResponseEntity<?> registerExpense(@RequestBody ExpenseDTO expense){
        HashMap<String, String> response = new HashMap<>();

        try {
            expenseService.createExpense(expense);
            response.put("msg", "Egreso registrado correctamente!");
            return ResponseEntity.ok().body(response);

        } catch (Exception e){
            logger.error(e.getMessage());
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body("Error "+ response);
        }
    }

    /**
     * Controllador para obtener todos los registros de egresos.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un listado de Registro de egresos.
     */
    @GetMapping(value = "/getAll")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<?> getExpense(){
        try {
            List<ExpenseDTOResponse> expenseList = expenseService.getAllExpense();
            return  ResponseEntity.ok().body(expenseList);
        } catch (BussinesException e){
            logger.error(e.getMessage());
            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }
    }
}
