package pe.edu.vallegrande.karinas_style.service.impl;

import pe.edu.vallegrande.karinas_style.model.DetailSale;
import pe.edu.vallegrande.karinas_style.repository.DetailSaleRepository;
import pe.edu.vallegrande.karinas_style.service.DetailSaleService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailSaleServiceImpl implements DetailSaleService {

    private final DetailSaleRepository repository;

    public DetailSaleServiceImpl(DetailSaleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DetailSale> listAll() {
        return repository.findAll();
    }

    @Override
    public DetailSale save(DetailSale detailSale) {
        return repository.save(detailSale);
    }

    @Override
    public DetailSale findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
