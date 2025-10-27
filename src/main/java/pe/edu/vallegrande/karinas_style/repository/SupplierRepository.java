package pe.edu.vallegrande.karinas_style.repository;

import pe.edu.vallegrande.karinas_style.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findByState(String state);
    
    Optional<Supplier> findByRuc(String ruc);
    
    Optional<Supplier> findByEmail(String email);
    
    @Query("SELECT s FROM Supplier s WHERE s.ruc = :ruc AND s.identifier != :id")
    Optional<Supplier> findByRucAndIdNot(@Param("ruc") String ruc, @Param("id") Integer id);
    
    @Query("SELECT s FROM Supplier s WHERE s.email = :email AND s.identifier != :id")
    Optional<Supplier> findByEmailAndIdNot(@Param("email") String email, @Param("id") Integer id);
    
    boolean existsByRuc(String ruc);
    boolean existsByEmail(String email);
}