package com.nicolasMorales.InventariumSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * @author Nicolas Morales.
 * Clase Entidad expense.
 */
@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ElementCollection
    private List<Long> products;

    private String userRegister;

    private String description;

    private LocalDateTime dateExpense = LocalDateTime.now();

}
