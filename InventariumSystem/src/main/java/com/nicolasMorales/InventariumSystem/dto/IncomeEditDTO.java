package com.nicolasMorales.InventariumSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Nicolas Morales.
 * DTO para editar registros ingresos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeEditDTO {

    private UUID id;
    private String observacion;
}
