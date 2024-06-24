package com.nicolasMorales.InventariumSystem.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Nicolas Morales.
 * Modelado de excepcion custom para el manejo de errores.
 */
@AllArgsConstructor
@Data
public class BussinesException extends Exception{

    public BussinesException(String msg){
        super(msg);
    }

}