package com.nicolasMorales.InventariumSystem.dto;

import lombok.Data;
import java.util.List;

/**
 * @author Nicolas Morales.
 * DTO para los egresos.
 */
@Data
public class ExpenseDTO {
    private String observacion;
    private String usuario;
    private List<ProductDTO> productos;
}
