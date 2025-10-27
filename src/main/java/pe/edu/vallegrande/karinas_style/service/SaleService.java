package pe.edu.vallegrande.karinas_style.service;

import pe.edu.vallegrande.karinas_style.dto.SaleRequest;
import pe.edu.vallegrande.karinas_style.dto.SaleResponse;

import java.util.List;

public interface SaleService {

    SaleResponse save(SaleRequest saleRequest);

    List<SaleResponse> findAll();

}
