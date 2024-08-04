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
@Table(name = "EGRESOS")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ElementCollection
    private List<Long> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_register_id", referencedColumnName = "id")
    private UserSec userRegister;

    private String description;

    private LocalDateTime dateExpense = LocalDateTime.now();

}
