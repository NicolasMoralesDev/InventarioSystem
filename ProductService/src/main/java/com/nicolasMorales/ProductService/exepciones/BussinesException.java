package com.nicolasMorales.ProductService.exepciones;
import lombok.AllArgsConstructor;
import lombok.Data;

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
