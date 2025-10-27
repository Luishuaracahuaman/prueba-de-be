package pe.edu.vallegrande.karinas_style.repository;

import pe.edu.vallegrande.karinas_style.model.DetailSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailSaleRepository extends JpaRepository<DetailSale, Long> {
    List<DetailSale> findBySaleId(Long saleId);
}
