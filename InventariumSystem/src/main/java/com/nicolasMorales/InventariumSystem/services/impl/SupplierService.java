package com.nicolasMorales.InventariumSystem.services.impl;


import com.nicolasMorales.InventariumSystem.entity.Supplier;
import com.nicolasMorales.InventariumSystem.exceptions.BussinesException;
import com.nicolasMorales.InventariumSystem.repository.ISupplierRepository;
import com.nicolasMorales.InventariumSystem.services.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SupplierService implements ISupplierService {

    @Autowired
    private ISupplierRepository supplierRepo;

    /**
     * @see ISupplierService#createSupplier(Supplier)
     */
    @Override
    public void createSupplier(Supplier nuevo) throws BussinesException {
        if (supplierRepo.findByNombre(nuevo.getNombre()) == null) {
            supplierRepo.save(nuevo);
        } else {
            throw new BussinesException("Este provedor ya existe");
        }
    }

    /**
     * @see ISupplierService#deleteSupplier(UUID)
     */
    @Override
    public String deleteSupplier(UUID id) {

        try {
            supplierRepo.deleteById(id);
            return "Proveedor Borrado!";
        } catch (Exception e){
            return "Error "+ e.getMessage();
        }
    }

    /**
     * @see ISupplierService#modifySupplier(Supplier)
     */
    @Override
    public void modifySupplier(Supplier edit) throws BussinesException, RuntimeException {

        try {
            supplierRepo.save(edit);
        } catch (RuntimeException e){
            throw new BussinesException("Error "+ e.getMessage()) ;
        }
    }

    /**
     * @see ISupplierService#getSuppliers()
     */
    @Override
    public List<Supplier> getSuppliers() throws BussinesException, RuntimeException {

        try {
            return supplierRepo.findAll();
        } catch (RuntimeException e){
            throw new BussinesException( e.getMessage());
        }
    }

    /**
     * @see ISupplierService#getSupplierById(UUID)
     */
    @Override
    public Supplier getSupplierById(UUID id) throws BussinesException {

        try {
            Supplier supplier = this.supplierRepo.findById(id).orElse(null);
            return supplier;
        } catch (RuntimeException e){
            throw new BussinesException( e.getMessage());
        }
    }

    /**
     * @see ISupplierService#deleteSuppliers(List)
     */
    @Override
    @Transactional
    public void deleteSuppliers(List<UUID> ids) throws BussinesException {

        try {
           List<Supplier> supplierList = ids.stream().map(id -> {
               try {
                   return this.getSupplierById(id);
               } catch (BussinesException e) {
                   throw new RuntimeException(e);
               }
           }).toList();
           supplierList.stream().forEach(supplier -> supplier.setBorrado(true));

        } catch (RuntimeException e){
            throw new BussinesException( e.getMessage());
        }
    }
}
