package com.nicolasMorales.IncomeService.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private LocalDate dateIncome = LocalDate.now();
    private String suppliers;
//    private estado;
    @ElementCollection
    private List <String> products;

}
