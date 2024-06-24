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
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private LocalDateTime dateIncome = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.EAGER)
    private Supplier supplier;
    @ElementCollection
    private List<Long> products;
    private boolean settled = false;

}