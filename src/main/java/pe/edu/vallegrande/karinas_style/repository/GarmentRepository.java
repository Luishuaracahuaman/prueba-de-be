package pe.edu.vallegrande.karinas_style.repository;

import pe.edu.vallegrande.karinas_style.model.Garment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;  // ← AGREGAR este import
import java.util.Optional;

@Repository
public interface GarmentRepository extends JpaRepository<Garment, Integer> {
    
    Optional<Garment> findById(Integer id);
    
    List<Garment> findByState(String state);  // ← Ahora funcionará
    
    // Agregar este método si no existe
    boolean existsById(Integer id);
}