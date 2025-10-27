package pe.edu.vallegrande.karinas_style.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CompraRequest {
    private Integer proveedorId;  // ← CON 'd' minúscula
    private BigDecimal total;
    private String estadoCompra;
    private List<CompraDetalleRequest> detalles;
    
    @Data
    public static class CompraDetalleRequest {
        private Integer prendaId;  // ← Cambiado de Long a Integer
        private Integer cantidad;
        private BigDecimal precioUnitario;
       
    }
}