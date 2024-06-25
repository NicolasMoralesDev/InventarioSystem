package com.nicolasMorales.InventariumSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
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
    private Set<Long> products;
    private LocalDateTime dateIncome = LocalDateTime.now();

}
