package pe.edu.vallegrande.karinas_style.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "sale", schema = "DEVELOPER_01")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identifier")
    private Long id;

    @Column(name = "sale_date", nullable = false)
    private LocalDateTime saleDate;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "client_identifier", nullable = false)
    private Long clientId; // OJO: si más adelante agregas Customer como entidad, aquí se puede reemplazar por @ManyToOne

    @Column(name = "employee_identifier", nullable = false)
    private Long employeeId; // Igual aquí, podría ser @ManyToOne si luego creas Employee.java

    @Column(name = "sale_status", nullable = false)
    private String saleStatus;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetailSale> detailSale;
}
