package microservice_reportes.Controller;
import microservice_reportes.Model.*;
import microservice_reportes.Service.DashboardService;
import microservice_reportes.Service.ReportesService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/reportes")
public class ReportesController {


    private final ReportesService reportesService;

    public ReportesController(ReportesService reportesService) {
        this.reportesService = reportesService;
    }


    @GetMapping("/movimientos/inventario")
    public PaginatedResponse<MovimientoInventario> obtenerMovimientosInventario(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
            @RequestParam(required = false) Integer insumoId,
            @RequestParam(required = false) Integer tipoMovimientoId,
            @RequestParam(required = false) String usuario,
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "50") Integer pageSize) {

        return reportesService.obtenerMovimientosInventario(
                fechaDesde, fechaHasta, insumoId, tipoMovimientoId, usuario, pageNumber, pageSize);
    }

    @GetMapping("/movimientos/caja")
    public PaginatedResponse<MovimientoCaja> obtenerMovimientosCaja(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
            @RequestParam(required = false) Integer tipoMovimientoId,
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) Integer sesionId,
            @RequestParam(required = false) String tipoPago,
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "50") Integer pageSize) {

        return reportesService.obtenerMovimientosCaja(
                fechaDesde, fechaHasta, tipoMovimientoId, usuario, sesionId, tipoPago, pageNumber, pageSize);
    }

    @GetMapping("/movimientos/consolidados")
    public PaginatedResponse<MovimientoConsolidado> obtenerMovimientosConsolidados(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta,
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String tipoMovimiento,
            @RequestParam(defaultValue = "1") Integer pageNumber,
            @RequestParam(defaultValue = "50") Integer pageSize) {

        return reportesService.obtenerMovimientosConsolidados(
                fechaDesde, fechaHasta, usuario, tipoMovimiento, pageNumber, pageSize);
    }

    @GetMapping("/estadisticas")
    public EstadisticasMovimientos obtenerEstadisticas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {

        return reportesService.obtenerEstadisticas(fechaDesde, fechaHasta);
    }

    @GetMapping("/tipos-movimiento")
    public List<TipoMovimiento> obtenerTiposMovimiento() {
        return reportesService.obtenerTiposMovimiento();
    }

    @GetMapping("/usuarios")
    public List<UsuarioMovimiento> obtenerUsuariosMovimientos() {
        return reportesService.obtenerUsuariosMovimientos();
    }
}