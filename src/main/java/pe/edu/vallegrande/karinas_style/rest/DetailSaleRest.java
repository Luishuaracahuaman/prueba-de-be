package pe.edu.vallegrande.karinas_style.rest;

import pe.edu.vallegrande.karinas_style.model.DetailSale;
import pe.edu.vallegrande.karinas_style.service.DetailSaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/detail-sale")
@CrossOrigin("*")
public class DetailSaleRest {

    private final DetailSaleService service;

    public DetailSaleRest(DetailSaleService service) {
        this.service = service;
    }

    @GetMapping
    public List<DetailSale> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public DetailSale findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public DetailSale save(@RequestBody DetailSale detailSale) {
        return service.save(detailSale);
    }

    @PutMapping("/{id}")
    public DetailSale update(@PathVariable Long id, @RequestBody DetailSale detailSale) {
        detailSale.setId(id);
        return service.save(detailSale);
    }

    /*@DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }*/
}
