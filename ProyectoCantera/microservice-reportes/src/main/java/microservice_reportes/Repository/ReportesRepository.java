package microservice_reportes.Repository;


import microservice_reportes.Model.*;

import java.time.LocalDate;
import java.util.List;

public interface ReportesRepository {
    PaginatedResponse<MovimientoInventario> obtenerMovimientosInventario(
            LocalDate fechaDesde, LocalDate fechaHasta, Integer insumoId,
            Integer tipoMovimientoId, String usuario, Integer pageNumber, Integer pageSize);

    PaginatedResponse<MovimientoCaja> obtenerMovimientosCaja(
            LocalDate fechaDesde, LocalDate fechaHasta, Integer tipoMovimientoId,
            String usuario, Integer sesionId, String tipoPago, Integer pageNumber, Integer pageSize);

    PaginatedResponse<MovimientoConsolidado> obtenerMovimientosConsolidados(
            LocalDate fechaDesde, LocalDate fechaHasta, String usuario,
            String tipoMovimiento, Integer pageNumber, Integer pageSize);

    EstadisticasMovimientos obtenerEstadisticas(LocalDate fechaDesde, LocalDate fechaHasta);
    List<TipoMovimiento> obtenerTiposMovimiento();
    List<UsuarioMovimiento> obtenerUsuariosMovimientos();
}