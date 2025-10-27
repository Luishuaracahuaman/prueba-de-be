package pe.edu.vallegrande.karinas_style.rest;

import pe.edu.vallegrande.karinas_style.model.Supplier;
import pe.edu.vallegrande.karinas_style.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/api/supplier")
public class SupplierRest {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<Supplier> suppliers = supplierService.findAll();
            return ResponseEntity.ok(suppliers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al obtener proveedores: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Optional<Supplier> supplierOpt = supplierService.findById(id);
            
            if (supplierOpt.isPresent()) {
                Supplier supplier = supplierOpt.get();
                
                // Crear response sin fecha
                Map<String, Object> response = new HashMap<>();
                response.put("identifier", supplier.getIdentifier());
                response.put("companyName", supplier.getCompanyName());
                response.put("ruc", supplier.getRuc());
                response.put("phone", supplier.getPhone());
                response.put("address", supplier.getAddress());
                response.put("email", supplier.getEmail());
                response.put("state", supplier.getState());
                
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al buscar proveedor: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Supplier supplier) {
        try {
            Supplier savedSupplier = supplierService.save(supplier);
            
            // Respuesta sin fecha
            Map<String, Object> response = new HashMap<>();
            response.put("identifier", savedSupplier.getIdentifier());
            response.put("companyName", savedSupplier.getCompanyName());
            response.put("ruc", savedSupplier.getRuc());
            response.put("phone", savedSupplier.getPhone());
            response.put("address", savedSupplier.getAddress());
            response.put("email", savedSupplier.getEmail());
            response.put("state", savedSupplier.getState());
            response.put("message", "Proveedor creado exitosamente");
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear proveedor: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Map<String, Object> supplierData) {
        try {
            // Verificar si el proveedor existe
            Optional<Supplier> existingSupplierOpt = supplierService.findById(id);
            if (!existingSupplierOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no encontrado con ID: " + id);
            }
            
            Supplier existingSupplier = existingSupplierOpt.get();
            
            // Actualizar solo los campos que vienen en el request
            if (supplierData.containsKey("companyName")) {
                existingSupplier.setCompanyName((String) supplierData.get("companyName"));
            }
            if (supplierData.containsKey("ruc")) {
                existingSupplier.setRuc((String) supplierData.get("ruc"));
            }
            if (supplierData.containsKey("phone")) {
                existingSupplier.setPhone((String) supplierData.get("phone"));
            }
            if (supplierData.containsKey("address")) {
                existingSupplier.setAddress((String) supplierData.get("address"));
            }
            if (supplierData.containsKey("email")) {
                existingSupplier.setEmail((String) supplierData.get("email"));
            }
            if (supplierData.containsKey("state")) {
                existingSupplier.setState((String) supplierData.get("state"));
            }
            
            Supplier updatedSupplier = supplierService.update(existingSupplier);
            
            // Respuesta sin fecha
            Map<String, Object> response = new HashMap<>();
            response.put("identifier", updatedSupplier.getIdentifier());
            response.put("companyName", updatedSupplier.getCompanyName());
            response.put("ruc", updatedSupplier.getRuc());
            response.put("phone", updatedSupplier.getPhone());
            response.put("address", updatedSupplier.getAddress());
            response.put("email", updatedSupplier.getEmail());
            response.put("state", updatedSupplier.getState());
            response.put("message", "Proveedor actualizado exitosamente");
            
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al actualizar proveedor: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLogic(@PathVariable Integer id) {
        try {
            supplierService.deleteLogic(id);
            return ResponseEntity.ok("Proveedor eliminado lógicamente con ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al eliminar proveedor: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> restoreLogic(@PathVariable Integer id) {
        try {
            supplierService.restoreLogic(id);
            return ResponseEntity.ok("Proveedor restaurado exitosamente con ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al restaurar proveedor: " + e.getMessage());
        }
    }

    @GetMapping("/filter/{state}")
    public ResponseEntity<?> findByState(@PathVariable String state) {
        try {
            List<Supplier> suppliers = supplierService.findByState(state);
            return ResponseEntity.ok(suppliers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al filtrar proveedores: " + e.getMessage());
        }
    }
}