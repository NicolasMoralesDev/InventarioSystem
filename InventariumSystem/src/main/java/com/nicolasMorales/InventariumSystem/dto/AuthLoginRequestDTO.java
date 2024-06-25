package com.nicolasMorales.InventariumSystem.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Nicolas Morales.
 * DTO para recibir informacion del login.
 */
public record AuthLoginRequestDTO ( @NotBlank String username,
                                    @NotBlank String password) {

}