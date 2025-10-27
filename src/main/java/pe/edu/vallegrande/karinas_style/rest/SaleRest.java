package pe.edu.vallegrande.karinas_style.rest;

import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.karinas_style.dto.SaleRequest;
import pe.edu.vallegrande.karinas_style.dto.SaleResponse;
import pe.edu.vallegrande.karinas_style.service.SaleService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/sale")
public class SaleRest {

    private final SaleService saleService;

    public SaleRest(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public List<SaleResponse> findAll() {
        return saleService.findAll();
    }

    @PostMapping("/save")
    public SaleResponse save(@RequestBody SaleRequest saleRequest) {
        return saleService.save(saleRequest);
    }
}
