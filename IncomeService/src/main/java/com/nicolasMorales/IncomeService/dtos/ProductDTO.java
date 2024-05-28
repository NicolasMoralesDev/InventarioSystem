package com.nicolasMorales.IncomeService.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author Nicolas Morales.
 * DTO para retornar la entidad Productos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

   private long codigo;
   private String nombre;
}
