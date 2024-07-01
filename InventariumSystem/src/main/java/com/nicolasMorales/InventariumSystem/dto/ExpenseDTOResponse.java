package com.nicolasMorales.InventariumSystem.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Nicolas Morales.
 * DTO para retornar los egresos.
 */
@Data
public class ExpenseDTOResponse {

    private UUID id;
    private String observacion;
    private String usuario;
    private List<ProductDTO> productos;
    private LocalDateTime fechaEgreso;
}
