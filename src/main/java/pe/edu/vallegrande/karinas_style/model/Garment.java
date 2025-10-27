package pe.edu.vallegrande.karinas_style.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "GARMENT", schema = "DEVELOPER_01")
public class Garment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDENTIFIER")
    private Integer id;  // ← CAMBIAR de Long a Integer

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @Column(name = "garment_size", nullable = false, length = 4)
    private String garmentSize;

    @Column(name = "color", nullable = false, length = 15)
    private String color;

    @Column(name = "suggested_price", nullable = false)
    private Double unitPrice;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "state", nullable = false, length = 1)
    private String state;

    @Column(name = "register_date", nullable = false)
    private LocalDate registerDate;
}