package pe.edu.vallegrande.karinas_style.service;  // ← SOLO service, NO service.impl

import pe.edu.vallegrande.karinas_style.dto.CompraRequest;
import pe.edu.vallegrande.karinas_style.dto.CompraResponse;
import java.util.List;
import java.util.Optional;

public interface CompraService {
    
    // Crear una compra completa (cabecera + detalles)
    CompraResponse crearCompra(CompraRequest compraRequest);
    
    // Obtener todas las compras
    List<CompraResponse> obtenerTodasLasCompras();
    
    // Obtener compra por ID con detalles
    Optional<CompraResponse> obtenerCompraPorId(Integer id);
    
    // Obtener compras por proveedor
    List<CompraResponse> obtenerComprasPorProveedor(Integer proveedorId);
    
    // Anular una compra (cambio de estado)
    void anularCompra(Integer id);
}