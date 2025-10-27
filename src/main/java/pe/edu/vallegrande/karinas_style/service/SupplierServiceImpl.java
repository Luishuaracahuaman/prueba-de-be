package pe.edu.vallegrande.karinas_style.service;

import pe.edu.vallegrande.karinas_style.model.Supplier;
import pe.edu.vallegrande.karinas_style.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository repository;

    @Override
    public List<Supplier> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Supplier> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Supplier save(Supplier supplier) {
        // Validar si RUC ya existe
        if (repository.existsByRuc(supplier.getRuc())) {
            throw new RuntimeException("El RUC " + supplier.getRuc() + " ya está registrado");
        }
        
        // Validar si email ya existe
        if (repository.existsByEmail(supplier.getEmail())) {
            throw new RuntimeException("El email " + supplier.getEmail() + " ya está registrado");
        }
        
        // Establecer estado activo por defecto
        supplier.setState("A");
        return repository.save(supplier);
    }

    @Override
    public Supplier update(Supplier supplier) {
        // Buscar el proveedor existente
        Optional<Supplier> existingSupplierOpt = repository.findById(supplier.getIdentifier());
        
        if (existingSupplierOpt.isPresent()) {
            Supplier existingSupplier = existingSupplierOpt.get();
            
            // Validar si RUC ya existe (excluyendo el actual)
            Optional<Supplier> supplierWithSameRuc = repository.findByRucAndIdNot(supplier.getRuc(), supplier.getIdentifier());
            if (supplierWithSameRuc.isPresent()) {
                throw new RuntimeException("El RUC " + supplier.getRuc() + " ya está registrado por otro proveedor");
            }
            
            // Validar si email ya existe (excluyendo el actual)
            Optional<Supplier> supplierWithSameEmail = repository.findByEmailAndIdNot(supplier.getEmail(), supplier.getIdentifier());
            if (supplierWithSameEmail.isPresent()) {
                throw new RuntimeException("El email " + supplier.getEmail() + " ya está registrado por otro proveedor");
            }
            
            // Mantener la fecha de registro original (IMPORTANTE)
            supplier.setRegisterDate(existingSupplier.getRegisterDate());
            
            // Mantener el estado original si no se envía
            if (supplier.getState() == null) {
                supplier.setState(existingSupplier.getState());
            }
            
            return repository.save(supplier);
        } else {
            throw new RuntimeException("Proveedor no encontrado con ID: " + supplier.getIdentifier());
        }
    }

    @Override
    public void deleteLogic(Integer id) {
        repository.findById(id).ifPresent(supplier -> {
            supplier.setState("I");
            repository.save(supplier);
        });
    }

    @Override
    public void restoreLogic(Integer id) {
        repository.findById(id).ifPresent(supplier -> {
            supplier.setState("A");
            repository.save(supplier);
        });
    }

    @Override
    public List<Supplier> findByState(String state) {
        return repository.findByState(state);
    }
}