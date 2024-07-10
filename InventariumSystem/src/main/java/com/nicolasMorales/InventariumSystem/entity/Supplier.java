package com.nicolasMorales.InventariumSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @author Nicolas Morales
 * Clase entidad provedor.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"nombre"})
)
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nombre;

    private String correo;

    private String tel;

    private boolean borrado = false;

}