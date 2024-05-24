package com.nicolasMorales.IncomeService.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Nicolas Morales.
 * DTO para retornar la entidad Productos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

   private String nombre;
   private UUID codigo;
}
