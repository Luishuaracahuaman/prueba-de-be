package pe.edu.vallegrande.karinas_style.dto;

import lombok.Data;
import pe.edu.vallegrande.karinas_style.model.Supplier;
import pe.edu.vallegrande.karinas_style.model.Garment;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CompraResponse {
    private Integer id;
    private BigDecimal total;
    private LocalDateTime fechaCompra;
    private String estadoCompra;
    private Supplier proveedor;
    private List<CompraDetalleResponse> detalles;
    
    @Data
    public static class CompraDetalleResponse {
        private Integer id;
        private Integer cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal subTotal;
        private Garment prenda;
    }
}