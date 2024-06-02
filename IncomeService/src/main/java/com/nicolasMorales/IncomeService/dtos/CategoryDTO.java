package com.nicolasMorales.IncomeService.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Nicolas Morales.
 * DTO para agregar categorias a los productos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private UUID id;
}
