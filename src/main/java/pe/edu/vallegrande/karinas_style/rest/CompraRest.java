package pe.edu.vallegrande.karinas_style.rest;

import pe.edu.vallegrande.karinas_style.dto.CompraRequest;
import pe.edu.vallegrande.karinas_style.dto.CompraResponse;
import pe.edu.vallegrande.karinas_style.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*")
public class CompraRest {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<CompraResponse> crearCompra(@RequestBody CompraRequest compraRequest) {
        try {
            // ===== LOGS DE DEBUG =====
            System.out.println("========== COMPRA REQUEST RECIBIDO ==========");
            System.out.println("Proveedor ID: " + compraRequest.getProveedorId());
            System.out.println("Total: " + compraRequest.getTotal());
            System.out.println("Estado Compra: " + compraRequest.getEstadoCompra());
            
            if (compraRequest.getDetalles() != null) {
                System.out.println("Cantidad de detalles: " + compraRequest.getDetalles().size());
                for (int i = 0; i < compraRequest.getDetalles().size(); i++) {
                    var detalle = compraRequest.getDetalles().get(i);
                    System.out.println("  Detalle [" + i + "]:");
                    System.out.println("    - Prenda ID: " + detalle.getPrendaId());
                    System.out.println("    - Cantidad: " + detalle.getCantidad());
                    System.out.println("    - Precio Unitario: " + detalle.getPrecioUnitario());
                   
                }
            } else {
                System.out.println("⚠️ Detalles es NULL");
            }
            System.out.println("=============================================");
            // ===== FIN LOGS =====
            
            CompraResponse compraCreada = compraService.crearCompra(compraRequest);
            System.out.println("✅ Compra creada exitosamente con ID: " + compraCreada.getId());
            return ResponseEntity.ok(compraCreada);
        } catch (RuntimeException e) {
            System.err.println("❌ ERROR al crear compra: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<CompraResponse>> obtenerTodasLasCompras() {
        List<CompraResponse> compras = compraService.obtenerTodasLasCompras();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompraResponse> obtenerCompraPorId(@PathVariable Integer id) {
        Optional<CompraResponse> compra = compraService.obtenerCompraPorId(id);
        return compra.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<CompraResponse>> obtenerComprasPorProveedor(@PathVariable Integer proveedorId) {
        List<CompraResponse> compras = compraService.obtenerComprasPorProveedor(proveedorId);
        return ResponseEntity.ok(compras);
    }

    @PutMapping("/{id}/anular")
    public ResponseEntity<Void> anularCompra(@PathVariable Integer id) {
        try {
            compraService.anularCompra(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}