package pe.edu.vallegrande.karinas_style.service;

import pe.edu.vallegrande.karinas_style.model.Supplier;
import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<Supplier> findAll();
    Optional<Supplier> findById(Integer id);
    Supplier save(Supplier supplier);
    Supplier update(Supplier supplier);
    void deleteLogic(Integer id);
    void restoreLogic(Integer id);
    List<Supplier> findByState(String state);
}