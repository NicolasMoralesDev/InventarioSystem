package com.nicolasMorales.InventariumSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Nicolas Morales.
 * DTO para Retornar Registros Ingresos.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomeDTOResponse {

    private UUID id;
    private String observacion;
    private String provedor;
    private List<ProductDTO> productos;
    private LocalDateTime fechaIngreso;
    private boolean saldado;

}
