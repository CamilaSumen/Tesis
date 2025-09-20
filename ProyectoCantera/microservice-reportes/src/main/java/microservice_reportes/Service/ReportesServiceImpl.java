package microservice_reportes.Service;

import microservice_reportes.Model.*;
import microservice_reportes.Repository.ReportesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportesServiceImpl implements ReportesService {

    @Autowired
    private ReportesRepository reportesRepository;

    @Override
    public PaginatedResponse<MovimientoInventario> obtenerMovimientosInventario(
            LocalDate fechaDesde, LocalDate fechaHasta, Integer insumoId,
            Integer tipoMovimientoId, String usuario, Integer pageNumber, Integer pageSize) {

        // Validaciones
        if (pageNumber == null || pageNumber < 1) pageNumber = 1;
        if (pageSize == null || pageSize < 1 || pageSize > 100) pageSize = 50;

        return reportesRepository.obtenerMovimientosInventario(
                fechaDesde, fechaHasta, insumoId, tipoMovimientoId, usuario, pageNumber, pageSize);
    }

    @Override
    public PaginatedResponse<MovimientoCaja> obtenerMovimientosCaja(
            LocalDate fechaDesde, LocalDate fechaHasta, Integer tipoMovimientoId,
            String usuario, Integer sesionId, String tipoPago, Integer pageNumber, Integer pageSize) {

        // Validaciones
        if (pageNumber == null || pageNumber < 1) pageNumber = 1;
        if (pageSize == null || pageSize < 1 || pageSize > 100) pageSize = 50;

        return reportesRepository.obtenerMovimientosCaja(
                fechaDesde, fechaHasta, tipoMovimientoId, usuario, sesionId, tipoPago, pageNumber, pageSize);
    }

    @Override
    public PaginatedResponse<MovimientoConsolidado> obtenerMovimientosConsolidados(
            LocalDate fechaDesde, LocalDate fechaHasta, String usuario,
            String tipoMovimiento, Integer pageNumber, Integer pageSize) {

        // Validaciones
        if (pageNumber == null || pageNumber < 1) pageNumber = 1;
        if (pageSize == null || pageSize < 1 || pageSize > 100) pageSize = 50;

        return reportesRepository.obtenerMovimientosConsolidados(
                fechaDesde, fechaHasta, usuario, tipoMovimiento, pageNumber, pageSize);
    }

    @Override
    public EstadisticasMovimientos obtenerEstadisticas(LocalDate fechaDesde, LocalDate fechaHasta) {
        return reportesRepository.obtenerEstadisticas(fechaDesde, fechaHasta);
    }

    @Override
    public List<TipoMovimiento> obtenerTiposMovimiento() {
        return reportesRepository.obtenerTiposMovimiento();
    }

    @Override
    public List<UsuarioMovimiento> obtenerUsuariosMovimientos() {
        return reportesRepository.obtenerUsuariosMovimientos();
    }
}