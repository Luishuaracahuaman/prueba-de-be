package pe.edu.vallegrande.karinas_style.service;

import pe.edu.vallegrande.karinas_style.model.DetailSale;

import java.util.List;

public interface DetailSaleService {
    List<DetailSale> listAll();
    DetailSale save(DetailSale detailSale);
    DetailSale findById(Long id);
    void delete(Long id);
}
