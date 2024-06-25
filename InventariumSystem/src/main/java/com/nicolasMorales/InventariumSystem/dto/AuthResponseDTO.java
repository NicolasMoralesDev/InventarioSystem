package com.nicolasMorales.InventariumSystem.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Nicolas Morales.
 * DTO para retornar informacion del login.
 */
@JsonPropertyOrder({"username", "message", "jwt", "status"})
public record AuthResponseDTO (String username,
                               String message,
                               String jwt,
                               boolean status) {
}