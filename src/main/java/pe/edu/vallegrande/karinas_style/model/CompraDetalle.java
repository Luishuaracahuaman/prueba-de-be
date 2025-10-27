package pe.edu.vallegrande.karinas_style.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "COMPRA_DETALLE", schema = "DEVELOPER_01")
public class CompraDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_detalle_seq")
    @SequenceGenerator(name = "compra_detalle_seq", sequenceName = "COMPRA_DETALLE_SEQ", allocationSize = 1, schema = "DEVELOPER_01")
    @Column(name = "IDENTIFIER")
    private Integer id;

    @Column(name = "QUANTITY", nullable = false)
    private Integer cantidad;

    @Column(name = "UNIT_PRICE", precision = 7, scale = 2, nullable = false)
    private BigDecimal precioUnitario;

    // ELIMINAR completamente esta línea:
    // private BigDecimal subTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PURCHASE_ID", nullable = false)
    private Compra compra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GARMENT_ID", nullable = false)
    private Garment prenda;
}