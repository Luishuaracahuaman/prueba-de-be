package pe.edu.vallegrande.karinas_style.repository;

import pe.edu.vallegrande.karinas_style.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    /*List<Sale> findById(Long id);*/
}
