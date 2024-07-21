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

    void createSupplier(Supplier nuevo) throws BussinesException, ExecutionException;

    String deleteSupplier(UUID id);

    void modifySupplier(Supplier edit) throws BussinesException, RuntimeException;

    List<Supplier> getSuppliers() throws BussinesException, RuntimeException;

    void deleteSuppliers(List<UUID> ids) throws BussinesException;
}
