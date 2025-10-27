package pe.edu.vallegrande.karinas_style.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "COMPRA", schema = "DEVELOPER_01")
public class Compra {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_seq")
@SequenceGenerator(name = "compra_seq", sequenceName = "COMPRA_SEQ", allocationSize = 1, schema = "DEVELOPER_01")
@Column(name = "IDENTIFIER")
private Integer id;

    @Column(name = "TOTAL_PURCHASE", nullable = false)
    private BigDecimal total;

    @Column(name = "PURCHASE_DATE", nullable = false)
    private LocalDateTime fechaCompra;

    @Column(name = "STATE", length = 1, nullable = false)
    private String estadoCompra;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUPPLIER_ID", nullable = false)
    private Supplier proveedor;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CompraDetalle> detalles = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.fechaCompra == null) {
            this.fechaCompra = LocalDateTime.now();
        }
        if (this.estadoCompra == null) {
            this.estadoCompra = "A";
        }
    }
}