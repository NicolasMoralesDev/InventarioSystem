package com.nicolasMorales.IncomeService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Nicolas Morales.
 * DTO para Retornar Registros Ingresos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDTOResponse {

    private String description;
    private List<String> suppliers;
    private LocalDateTime fechaIngreso;

}
