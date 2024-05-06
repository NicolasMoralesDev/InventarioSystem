package com.nicolasMorales.IncomeService.controllers;


import com.nicolasMorales.IncomeService.dtos.IncomeDTO;
import com.nicolasMorales.IncomeService.models.Income;
import com.nicolasMorales.IncomeService.services.IIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/income")
@CrossOrigin(origins = "*")
public class ControllerIncome {

    @Autowired
    private IIncomeService incomeService;

    @PostMapping("/register")
    public ResponseEntity<?> registerIncome(@RequestBody IncomeDTO income){

        HashMap<String, String> response = new HashMap<>();
        try {

            String status = incomeService.createIncome(income);

            if (status == "Ingreso registrado!") {

                response.put("msg", status );

                return ResponseEntity.ok().body(response);

            } else {

                response.put("error", status);
                return  ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e){
            response.put("error", e.getMessage());
            return  ResponseEntity.badRequest().body("Error "+ response);
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getCIncome(){

        try {

            return  ResponseEntity.ok().body(incomeService.getAllIncome());

        } catch (Exception e){

            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getIncomeById(@PathVariable UUID id){

        try {

            return  ResponseEntity.ok().body(incomeService.getIncomeById(id));

        } catch (Exception e){

            return  ResponseEntity.badRequest().body("Error "+ e.getMessage());
        }

    }


    @DeleteMapping("/delete/{id}")
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


    @PutMapping("/put")
    public  ResponseEntity<?> editIncome(@RequestBody Income edit){

        HashMap<String, String> response = new HashMap<>();

        try {

            String status = incomeService.editIncome(edit);

            if (status == "Registro Modificado!") {

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
