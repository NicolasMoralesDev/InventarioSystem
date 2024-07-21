package com.nicolasMorales.InventariumSystem.services;

import com.nicolasMorales.InventariumSystem.entity.Supplier;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 *  @author Nicolas Morales.
 *  Interfaz para los servicios de Provedores.
 */
public interface ISupplierService {

    /**
     * Registra un nuevo provedor.
     *
     * @param nuevo {@link Supplier}
     * @throws BussinesException Error
     */
    void createSupplier(Supplier nuevo) throws BussinesException, ExecutionException;

    /**
     * Borra un provedor por su id.
     *
     * @param id {@link UUID}
     * @throws BussinesException Error
     */
    String deleteSupplier(UUID id);

    /**
     * Edita un provedor.
     *
     * @param edit {@link Supplier}
     * @throws RuntimeException Error
     * @throws BussinesException Error
     */
    void modifySupplier(Supplier edit) throws BussinesException, RuntimeException;

    /**
     * Obtiene todos los provedores.
     *
     * @throws BussinesException Error
     */
    List<Supplier> getSuppliers() throws BussinesException, RuntimeException;

    /**
     * Obtiene un provedor por su id.
     *
     * @param id {@link UUID}
     * @throws BussinesException Error
     */
    Supplier getSupplierById(UUID id) throws BussinesException;

    /**
     * Borra masivamente provedores.
     *
     * @param ids {@link List <UUID>}
     * @throws BussinesException Error
     */
    void deleteSuppliers(List<UUID> ids) throws BussinesException;
}
