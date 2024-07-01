package com.nicolasMorales.InventariumSystem.dto;

import lombok.Data;

import java.util.List;

/**
 *  @author Nicolas Morales.
 *  DTO para la entidad Usuarios.
 */
@Data
public class UserDTO {

    private String username;

    private String nombreCompleto;

    private int dni;

    private List <RoleDTO> roles;

    private boolean enabled;
}
