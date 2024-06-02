package com.nicolasMorales.IncomeService.dtos;


import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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
   private CategoryDTO categoria;
   private String descripcion;
   private String img;
   private List <SubCategoryDTO> subCategoria;
   private String marca;
   private double precio;
   private int cant;
}
