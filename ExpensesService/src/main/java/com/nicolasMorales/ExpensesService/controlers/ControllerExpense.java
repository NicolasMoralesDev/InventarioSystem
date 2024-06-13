package com.nicolasMorales.ExpensesService.controlers;

import com.nicolasMorales.ExpensesService.models.Expense;
import com.nicolasMorales.ExpensesService.services.impl.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author Nicolas Morales.
 * Clase controller para los egresos.
 */
@RestController
@RequestMapping("/api/v1/expense")
@CrossOrigin(origins = "*")
public class ControllerExpense {

    @Autowired
    private ExpenseService expenseService;

    /**
     * Controllador para registrar egresos.
     * @param expense Recibe los datos a registrar.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje.
     */
    @PostMapping(value = "/register")
    public ResponseEntity<?> registerExpense(@RequestBody Expense expense){
        HashMap<String, String> response = new HashMap<>();

        try {
            expenseService.createExpense(expense);
            response.put("msg", "Egreso registrado correctamente!");
            return ResponseEntity.ok().body(response);

        } catch (Exception e){
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body("Error "+ response);
        }

    }

    /**
     * Controllador para obtener todos los registros de egresos.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un listado de Registro de egresos.
     */
    @GetMapping(value = "/getAll")
    public ResponseEntity<?> getExpense(){
        try {
            return  ResponseEntity.ok().body(expenseService.getAllExpense());
        } catch (Exception e){
            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }
    }
}
