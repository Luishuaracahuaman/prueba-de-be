package pe.edu.vallegrande.karinas_style.service.impl;

import pe.edu.vallegrande.karinas_style.model.Garment;
import pe.edu.vallegrande.karinas_style.repository.GarmentRepository;
import pe.edu.vallegrande.karinas_style.service.GarmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

@Slf4j
@Service
public class GarmentServiceImpl implements GarmentService {

    private final GarmentRepository repository;
    private final DataSource dataSource;

    @Autowired
    public GarmentServiceImpl(GarmentRepository repository, DataSource dataSource) {
        this.repository = repository;
        this.dataSource = dataSource;
    }

    @Override
    public List<Garment> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Garment> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Garment save(Garment garment) {
        garment.setState("A");
        return repository.save(garment);
    }

    @Override
    public Garment update(Garment garment) {
        garment.setState("A");
        return repository.save(garment);
    }

    @Override
    public void deleteLogic(Integer id) {
        Optional<Garment> garmentOpt = repository.findById(id);
        garmentOpt.ifPresent(garment -> {
            garment.setState("I");
            repository.save(garment);
        });
    }

    @Override
    public void restoreLogic(Integer id) {
        Optional<Garment> garmentOpt = repository.findById(id);
        garmentOpt.ifPresent(garment -> {
            garment.setState("A");
            repository.save(garment);
        });
    }

    @Override
    public List<Garment> findByState(String state) {
        return repository.findByState(state);
    }

    // Para generación de reportes
    @Override
    public byte[] generateJasperPdfReport() throws Exception {
        // Cargar archivo .jasper en src/main/resources/reports (SIN USAR IMÁGENES EN EL JASPER)
        InputStream jasperStream = new ClassPathResource("reports/Garment.jasper").getInputStream();
        // Sin parámetros
        HashMap<String, Object> params = new HashMap<>();
        // Llenar reporte con conexión a Oracle Cloud con application.yml | aplicación.properties
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, params, dataSource.getConnection());
        // Exportar a PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);

        /* 
        Para imagen en reports, la imagen estará la misma carpeta reports; 
        lo siguiente se usará en el jasperReport o .jrxml:
        <imageExpression><![CDATA[this.getClass().getClassLoader().getResource("reports/leaf_banner_red.png").getPath()]]></imageExpression> 
        */
    }
}