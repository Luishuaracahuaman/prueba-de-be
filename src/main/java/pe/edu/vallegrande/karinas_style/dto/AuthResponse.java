package pe.edu.vallegrande.karinas_style.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String username;
    private String rol;
    
    // Constructor necesario
    public AuthResponse(String token, String username, String rol) {
        this.token = token;
        this.username = username;
        this.rol = rol;
    }
    
    // Getters y setters (Lombok los genera automáticamente con @Data)
}