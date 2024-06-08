package com.nicolasMorales.IncomeService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nicolas Morales.
 * DTO para agregar provedores a los ingresos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {

    private String nombre;
}
