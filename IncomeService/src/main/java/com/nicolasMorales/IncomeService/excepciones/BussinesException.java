package com.nicolasMorales.IncomeService.excepciones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nicolas Morales.
 * Modelado de Excepcion custom para el manejo de errores.
 */
@AllArgsConstructor
@Data
public class BussinesException extends Exception{

    public BussinesException(String msg){
        super(msg);
    }

}
