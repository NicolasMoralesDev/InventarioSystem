package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.dto.IncomeDTO;
import com.nicolasMorales.InventariumSystem.dto.IncomeDTOResponse;
import com.nicolasMorales.InventariumSystem.dto.IncomeEditDTO;
import com.nicolasMorales.InventariumSystem.entity.Income;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;

import java.util.List;
import java.util.UUID;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de Ingresos.
 */
public interface IIncomeService {

    /**
     * Obtiene todos los ingresos.
     *
     * @throws BussinesException Error
     */
     List<IncomeDTOResponse> getAllIncome() throws BussinesException;

    /**
     * Obtiene un ingreso por su id.
     *
     * @param id {@link UUID}
     * @throws BussinesException Error
     */
     Income getIncomeById(UUID id) throws BussinesException;

    /**
     * Registra un nuevo ingreso.
     *
     * @param nuevo {@link IncomeDTO}
     * @throws BussinesException Error
     */
     void createIncome(IncomeDTO nuevo) throws BussinesException;

    /**
     * Borra un ingreso por su id.
     *
     * @param id {@link UUID}
     * @throws BussinesException Error
     */
     String deleteByIdIncome(UUID id);

    /**
     * Edita un ingreso.
     *
     * @param edit {@link IncomeEditDTO}
     * @throws BussinesException Error
     */
     void editIncome(IncomeEditDTO edit) throws BussinesException;
}
