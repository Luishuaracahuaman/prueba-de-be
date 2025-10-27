package pe.edu.vallegrande.karinas_style.dto;

import lombok.Data;
import java.util.List;

@Data
public class SaleRequest {

    private List<DetailRequest> detailSale;

@Data
public static class DetailRequest {
    private Integer garmentId;  // ← CAMBIAR de Long a Integer
    private Integer quantity;
    // ... otros campos
}
}
