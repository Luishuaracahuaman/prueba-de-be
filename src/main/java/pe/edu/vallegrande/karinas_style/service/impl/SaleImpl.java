package pe.edu.vallegrande.karinas_style.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.vallegrande.karinas_style.dto.SaleRequest;
import pe.edu.vallegrande.karinas_style.dto.SaleResponse;
import pe.edu.vallegrande.karinas_style.model.Sale;
import pe.edu.vallegrande.karinas_style.model.DetailSale;
import pe.edu.vallegrande.karinas_style.model.Garment;
import pe.edu.vallegrande.karinas_style.repository.GarmentRepository;
import pe.edu.vallegrande.karinas_style.repository.SaleRepository;
import pe.edu.vallegrande.karinas_style.repository.DetailSaleRepository;
import pe.edu.vallegrande.karinas_style.service.SaleService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SaleImpl implements SaleService {

    private final GarmentRepository garmentRepository;
    private final SaleRepository saleRepository;
    private final DetailSaleRepository detailSaleRepository;

    public SaleImpl(GarmentRepository garmentRepository,
            SaleRepository saleRepository,
            DetailSaleRepository detailSaleRepository) {
        this.garmentRepository = garmentRepository;
        this.saleRepository = saleRepository;
        this.detailSaleRepository = detailSaleRepository;
    }

    @Override
    @Transactional
    public SaleResponse save(SaleRequest request) {
        Sale sale = new Sale();
        sale.setSaleDate(LocalDateTime.now());
        sale.setSaleStatus("A");

        List<DetailSale> detailSale = new ArrayList<>();
        double total = 0;

        for (SaleRequest.DetailRequest dr : request.getDetailSale()) {
            Garment garment = garmentRepository.findById(dr.getGarmentId())
                    .orElseThrow(() -> new RuntimeException("Prenda no encontrada"));

            if (garment.getStock() < dr.getQuantity()) {
                throw new RuntimeException("Stock insuficiente para: " + garment.getName());
            }

            // actualizar stock
            garment.setStock(garment.getStock() - dr.getQuantity());
            garmentRepository.save(garment);

            // calcular subtotal
            double subtotal = garment.getUnitPrice() * dr.getQuantity();

            DetailSale detail = new DetailSale();
            detail.setGarment(garment);
            detail.setQuantity(dr.getQuantity());
            detail.setSale(sale);

            detailSale.add(detail);
            total += subtotal;
        }

        sale.setTotal(total);
        sale.setDetailSale(detailSale);

        Sale saved = saleRepository.save(sale); // guarda en cascada la venta + detalles
        return toDto(saved);
    }

    @Override
    public List<SaleResponse> findAll() {
        List<Sale> sales = saleRepository.findAll();

        return sales.stream().map(this::toDto).collect(Collectors.toList());
    }

    private SaleResponse toDto(Sale sale) {
        SaleResponse dto = new SaleResponse();
        dto.setSaleId(sale.getId());
        dto.setSaleDate(sale.getSaleDate());
        dto.setTotal(sale.getTotal());
        dto.setState(sale.getSaleStatus());

        // Traer detalles desde el repositorio
        List<DetailSale> details = detailSaleRepository.findBySaleId(sale.getId());
        dto.setDetailSale(details.stream().map(d -> {
            SaleResponse.DetailDto dd = new SaleResponse.DetailDto();
            //dd.setPrice(d.getGarment().getUnitPrice());
            dd.setQuantity(d.getQuantity());
            dd.setSubtotal(d.getSubtotal());
            return dd;
        }).collect(Collectors.toList()));

        return dto;
    }
}