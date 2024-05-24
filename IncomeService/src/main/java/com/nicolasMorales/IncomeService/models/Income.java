package com.nicolasMorales.IncomeService.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * @author Nicolas Morales.
 * Clase Entidad Ingresos.
 */
@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private LocalDateTime dateIncome = LocalDateTime.now();
    @ElementCollection
    private List <String> suppliers;
//    private estado;
    @ElementCollection
    private List <Long> products;

}
