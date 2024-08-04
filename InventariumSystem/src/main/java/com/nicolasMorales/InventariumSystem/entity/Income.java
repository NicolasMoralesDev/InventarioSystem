package com.nicolasMorales.InventariumSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Nicolas Morales.
 * Clase entidad ingreso.
 */
@Entity
@Data
@Table(name = "INGRESOS")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_register_id", referencedColumnName = "id")
    private UserSec userRegister;

    private LocalDateTime dateIncome = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplier;

    @ElementCollection
    private List<Long> products;

    private boolean settled = false;

}