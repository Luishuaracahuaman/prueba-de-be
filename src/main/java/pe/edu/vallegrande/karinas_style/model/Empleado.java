package pe.edu.vallegrande.karinas_style.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "EMPLEADO", schema = "DEVELOPER_01")  // ← CAMBIAR a "EMPLEADO" en mayúsculas
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identifier")
    private Integer id;

    @Column(name = "nombres", nullable = false, length = 50)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 60)
    private String apellidos;

    @Column(name = "dni", nullable = false, length = 8)
    private String dni;

    @Column(name = "telefono", nullable = false, length = 9)
    private String telefono;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "rol", nullable = false, length = 20)
    private String rol; // ADMIN, COMPRAS, VENTAS

    @Column(name = "state", nullable = false, length = 1)
    private String state;

    @Column(name = "register_date", nullable = false)
    private LocalDateTime registerDate;

    @PrePersist
    public void prePersist() {
        if (this.registerDate == null) {
            this.registerDate = LocalDateTime.now();
        }
        if (this.state == null) {
            this.state = "A";
        }
    }
}