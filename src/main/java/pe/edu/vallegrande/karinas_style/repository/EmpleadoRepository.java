package pe.edu.vallegrande.karinas_style.repository;

import pe.edu.vallegrande.karinas_style.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    Optional<Empleado> findByUsername(String username);
    Optional<Empleado> findByEmail(String email);
    Optional<Empleado> findByDni(String dni);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
}