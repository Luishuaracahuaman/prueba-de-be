package pe.edu.vallegrande.karinas_style.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleResponse {

    private Long saleId;
    private LocalDateTime saleDate;
    private Double total;
    private String state;  // alineado con el modelo Sale
    private List<DetailDto> detailSale;

    @Data
    public static class DetailDto {
        //private Double price;
        private Integer quantity;
        private Double subtotal;
    }
}
