package pe.edu.vallegrande.karinas_style.service;

import pe.edu.vallegrande.karinas_style.model.Garment;
import java.util.List;
import java.util.Optional;

public interface GarmentService {
    List<Garment> findAll();
    Optional<Garment> findById(Integer id);  // ← CAMBIAR Long por Integer
    Garment save(Garment garment);
    Garment update(Garment garment);
    void deleteLogic(Integer id);  // ← CAMBIAR Long por Integer
    void restoreLogic(Integer id);  // ← CAMBIAR Long por Integer
    List<Garment> findByState(String state);
    byte[] generateJasperPdfReport() throws Exception;
}