package com.nicolasMorales.InventariumSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Nicolas Morales.
 * DTO para Retornar Registros Ingresos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeEditDTO {

    private UUID id;
    private String observacion;
}
