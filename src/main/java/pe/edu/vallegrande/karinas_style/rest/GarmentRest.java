package pe.edu.vallegrande.karinas_style.rest;

import pe.edu.vallegrande.karinas_style.model.Garment;
import pe.edu.vallegrande.karinas_style.service.GarmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/garment")
public class GarmentRest {

    private final GarmentService garmentService;

    @Autowired
    public GarmentRest(GarmentService garmentService) {
        this.garmentService = garmentService;
    }

    @GetMapping
    public List<Garment> findAll() {
        return garmentService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Garment> findById(@PathVariable Integer id) {  // ← CAMBIAR Long por Integer
        return garmentService.findById(id);
    }

    @PostMapping("/save")
    public Garment save(@RequestBody Garment garment) {
        return garmentService.save(garment);
    }

    @PutMapping("/update")
    public Garment update(@RequestBody Garment garment) {
        return garmentService.update(garment);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLogic(@PathVariable Integer id) {  // ← CAMBIAR Long por Integer
        garmentService.deleteLogic(id);
    }

    @PatchMapping("/restore/{id}")
    public void restoreLogic(@PathVariable Integer id) {  // ← CAMBIAR Long por Integer
        garmentService.restoreLogic(id);
    }

    @GetMapping("/filter/{state}")
    public List<Garment> findByState(@PathVariable(required = false) String state) {
        if (state != null && !state.isEmpty()) {
            return garmentService.findByState(state.toUpperCase());
        } else {
            return garmentService.findAll();
        }
    }

    // Para generación de reportes
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generateJasperPdfReport() {
        try {
            byte[] pdf = garmentService.generateJasperPdfReport();
            return ResponseEntity.ok()
                    //Renombrar el archivo PDF al descargar
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_prendas.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}