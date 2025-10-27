package pe.edu.vallegrande.karinas_style.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "SUPPLIER", schema = "DEVELOPER_01")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identifier")
    private Integer identifier;

    @Column(name = "company_name", length = 100, nullable = false)
    private String companyName;

    @Column(name = "ruc", length = 11, nullable = false)
    private String ruc;

    @Column(name = "phone", length = 9, nullable = false)
    private String phone;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "state", length = 1, nullable = false)
    private String state;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime registerDate;

    @PrePersist
    @PreUpdate
    public void prePersist() {
        if (this.registerDate == null) {
            this.registerDate = LocalDateTime.now();
        }
        if (this.state == null) {
            this.state = "A";
        }
    }
}