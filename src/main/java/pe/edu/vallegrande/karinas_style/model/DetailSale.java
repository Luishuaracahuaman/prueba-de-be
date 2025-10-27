package pe.edu.vallegrande.karinas_style.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DETAILSALE", schema = "DEVELOPER_01")
public class DetailSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identifier")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sale_identifier", nullable = false)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "garment_identifier", nullable = false)
    private Garment garment;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private Double unitPrice;

    // En tu script SQL, subtotal es columna virtual, aquí lo podemos calcular dinámicamente
    @Transient
    public Double getSubtotal() {
        return unitPrice != null && quantity != null ? unitPrice * quantity : 0.0;
    }
}
