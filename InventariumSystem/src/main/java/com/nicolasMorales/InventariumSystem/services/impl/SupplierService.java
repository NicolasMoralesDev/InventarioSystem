package com.nicolasMorales.InventariumSystem.services.impl;


import com.nicolasMorales.InventariumSystem.entity.Supplier;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.repository.ISupplierRepository;
import com.nicolasMorales.InventariumSystem.services.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private ISupplierRepository supplierRepo;

    @Override
    public void createSupplier(Supplier nuevo) throws BussinesException {
        if (supplierRepo.findByNombre(nuevo.getNombre()) == null) {
            supplierRepo.save(nuevo);
        } else {
            throw new BussinesException("Este provedor ya existe");
        }
    }

    @Override
    public String deleteSupplier(UUID id) {

        try {
            supplierRepo.deleteById(id);
            return "Proveedor Borrado!";
        } catch (Exception e){
            return "Error "+ e.getMessage();
        }
    }

    @Override
    public void modifySupplier(Supplier edit) throws BussinesException, RuntimeException {

        try {
            supplierRepo.save(edit);
        } catch (RuntimeException e){
            throw new BussinesException("Error "+ e.getMessage()) ;
        }
    }

    @Override
    public List<Supplier> getSuppliers() throws BussinesException, RuntimeException {
        try {
            return supplierRepo.findAll();
        } catch (RuntimeException e){
            throw new BussinesException( e.getMessage());
        }
    }
}
