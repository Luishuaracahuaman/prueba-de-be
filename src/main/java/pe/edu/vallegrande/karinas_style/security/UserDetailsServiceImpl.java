package pe.edu.vallegrande.karinas_style.security;

import pe.edu.vallegrande.karinas_style.model.Empleado;
import pe.edu.vallegrande.karinas_style.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Empleado empleado = empleadoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Verificar que el empleado esté activo
        if (!"A".equals(empleado.getState())) {
            throw new UsernameNotFoundException("Usuario inactivo: " + username);
        }

        // CAMBIO: Usar authorities() en lugar de roles()
        return User.builder()
                .username(empleado.getUsername())
                .password(empleado.getPassword())
                .authorities(empleado.getRol())  // ← SIN prefijo ROLE_
                .build();
    }
}