package pe.edu.vallegrande.karinas_style.repository;

import pe.edu.vallegrande.karinas_style.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {
    
    // Buscar compras por estado
    List<Compra> findByEstadoCompra(String estadoCompra);
    
    // CORREGIDO: Usar @Query en lugar del método derivado
@Query("SELECT c FROM Compra c WHERE c.proveedor.identifier = :proveedorId")
List<Compra> findByProveedorIdentifier(@Param("proveedorId") Integer proveedorId);
    // Buscar compra con detalles (usando JOIN FETCH)
    @Query("SELECT c FROM Compra c JOIN FETCH c.detalles WHERE c.id = :id")
    Optional<Compra> findByIdWithDetalles(@Param("id") Integer id);
    
    // Listar todas las compras con detalles
    @Query("SELECT DISTINCT c FROM Compra c JOIN FETCH c.detalles")
    List<Compra> findAllWithDetalles();
    
    // CORREGIDO: Usar @Query
    @Query("SELECT COUNT(c) > 0 FROM Compra c WHERE c.proveedor.identifier = :proveedorId AND c.fechaCompra = :fechaCompra")
    boolean existsByProveedorAndFecha(@Param("proveedorId") Integer proveedorId, @Param("fechaCompra") LocalDateTime fechaCompra);
}