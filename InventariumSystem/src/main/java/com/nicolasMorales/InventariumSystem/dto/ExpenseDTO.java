package com.nicolasMorales.InventariumSystem.dto;

import com.nicolasMorales.InventariumSystem.entity.Product;
import jakarta.annotation.Nullable;
import lombok.Data;

import java.time.LocalDateTime;
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
