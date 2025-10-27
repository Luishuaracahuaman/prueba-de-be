package pe.edu.vallegrande.karinas_style.service.impl;

import pe.edu.vallegrande.karinas_style.dto.CompraRequest;
import pe.edu.vallegrande.karinas_style.dto.CompraResponse;
import pe.edu.vallegrande.karinas_style.model.Compra;
import pe.edu.vallegrande.karinas_style.model.CompraDetalle;
import pe.edu.vallegrande.karinas_style.model.Supplier;
import pe.edu.vallegrande.karinas_style.model.Garment;
import pe.edu.vallegrande.karinas_style.repository.CompraRepository;
import pe.edu.vallegrande.karinas_style.repository.SupplierRepository;
import pe.edu.vallegrande.karinas_style.repository.GarmentRepository;
import pe.edu.vallegrande.karinas_style.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private GarmentRepository garmentRepository;

    @Override
    @Transactional
    public CompraResponse crearCompra(CompraRequest compraRequest) {
        // Validar que el proveedor existe
        Supplier proveedor = supplierRepository.findById(compraRequest.getProveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + compraRequest.getProveedorId()));

        // Validar que el proveedor esté activo
        if (!"A".equals(proveedor.getState())) {
            throw new RuntimeException("El proveedor no está activo");
        }

        // Crear la compra (cabecera)
        Compra compra = new Compra();
        compra.setProveedor(proveedor);
        compra.setTotal(compraRequest.getTotal());
        compra.setEstadoCompra(compraRequest.getEstadoCompra() != null ? compraRequest.getEstadoCompra() : "A");

        // Procesar detalles
        List<CompraDetalle> detalles = compraRequest.getDetalles().stream()
                .map(detalleRequest -> {
                    // Validar que la prenda existe
                    Garment prenda = garmentRepository.findById(detalleRequest.getPrendaId())
                            .orElseThrow(() -> new RuntimeException("Prenda no encontrada con ID: " + detalleRequest.getPrendaId()));

                    // Validar que la prenda esté activa
                    if (!"A".equals(prenda.getState())) {
                        throw new RuntimeException("La prenda no está activa: " + prenda.getName());
                    }

                    // ACTUALIZAR STOCK - AUMENTAR stock porque es una COMPRA
                    prenda.setStock(prenda.getStock() + detalleRequest.getCantidad());
                    garmentRepository.save(prenda);

                    // Crear detalle
                    CompraDetalle detalle = new CompraDetalle();
                    detalle.setCompra(compra);
                    detalle.setPrenda(prenda);
                    detalle.setCantidad(detalleRequest.getCantidad());
                    detalle.setPrecioUnitario(detalleRequest.getPrecioUnitario());

                    return detalle;
                })
                .collect(Collectors.toList());

        compra.setDetalles(detalles);

        // Guardar la compra completa (cabecera + detalles)
        Compra compraGuardada = compraRepository.save(compra);

        // Convertir a Response
        return convertirACompraResponse(compraGuardada);
    }

    @Override
    public List<CompraResponse> obtenerTodasLasCompras() {
       return compraRepository.findAll().stream()
                .map(this::convertirACompraResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CompraResponse> obtenerCompraPorId(Integer id) {
        return compraRepository.findById(id)
                .map(this::convertirACompraResponse);
    }

    @Override
    public List<CompraResponse> obtenerComprasPorProveedor(Integer proveedorId) {
        return compraRepository.findByProveedorIdentifier(proveedorId).stream()
                .map(this::convertirACompraResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void anularCompra(Integer id) {
        Compra compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra no encontrada con ID: " + id));
        compra.setEstadoCompra("I");
        compraRepository.save(compra);
    }

    private CompraResponse convertirACompraResponse(Compra compra) {
        CompraResponse response = new CompraResponse();
        response.setId(compra.getId());
        response.setTotal(compra.getTotal());
        response.setFechaCompra(compra.getFechaCompra());
        response.setEstadoCompra(compra.getEstadoCompra());
        response.setProveedor(compra.getProveedor());

        // Convertir detalles
        List<CompraResponse.CompraDetalleResponse> detallesResponse = compra.getDetalles().stream()
                .map(detalle -> {
                    CompraResponse.CompraDetalleResponse detalleResponse = new CompraResponse.CompraDetalleResponse();
                    detalleResponse.setId(detalle.getId());
                    detalleResponse.setCantidad(detalle.getCantidad());
                    detalleResponse.setPrecioUnitario(detalle.getPrecioUnitario());
                    detalleResponse.setPrenda(detalle.getPrenda());
                    return detalleResponse;
                })
                .collect(Collectors.toList());

        response.setDetalles(detallesResponse);
        return response;
    }
}