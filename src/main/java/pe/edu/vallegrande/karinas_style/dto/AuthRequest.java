package pe.edu.vallegrande.karinas_style.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}