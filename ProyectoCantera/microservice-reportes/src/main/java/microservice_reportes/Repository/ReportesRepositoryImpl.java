package microservice_reportes.Repository;

import microservice_reportes.Model.*;
import microservice_reportes.Repository.StoredProcedure.StoredProcedureR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

@Repository
public class ReportesRepositoryImpl implements ReportesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public PaginatedResponse<MovimientoInventario> obtenerMovimientosInventario(
            LocalDate fechaDesde, LocalDate fechaHasta, Integer insumoId,
            Integer tipoMovimientoId, String usuario, Integer pageNumber, Integer pageSize) {

        try {
            List<MovimientoInventario> movimientos = jdbcTemplate.query(
                    StoredProcedureR.SP_OBTENER_MOVIMIENTOS_INVENTARIO,
                    new Object[]{
                            fechaDesde != null ? Date.valueOf(fechaDesde) : null,
                            fechaHasta != null ? Date.valueOf(fechaHasta) : null,
                            insumoId,
                            tipoMovimientoId,
                            usuario,
                            pageNumber,
                            pageSize
                    },
                    (rs, rowNum) -> {
                        MovimientoInventario movimiento = new MovimientoInventario();
                        movimiento.setMovimientoId(rs.getInt("MovimientoId"));
                        movimiento.setFechaMovimiento(rs.getTimestamp("FechaMovimiento").toLocalDateTime());
                        movimiento.setTipoMovimiento(rs.getString("TipoMovimiento"));
                        movimiento.setInsumoId(rs.getInt("InsumoId"));
                        movimiento.setNombreInsumo(rs.getString("NombreInsumo"));
                        movimiento.setUnidadMedida(rs.getString("UnidadMedida"));
                        movimiento.setCategoriaInsumo(rs.getString("CategoriaInsumo"));
                        movimiento.setCantidadMovimiento(rs.getBigDecimal("CantidadMovimiento"));
                        movimiento.setStockAnterior(rs.getBigDecimal("StockAnterior"));
                        movimiento.setStockNuevo(rs.getBigDecimal("StockNuevo"));
                        movimiento.setObservaciones(rs.getString("Observaciones"));
                        movimiento.setPedidoId(rs.getObject("PedidoId") != null ? rs.getInt("PedidoId") : null);
                        movimiento.setUsuario(rs.getString("Usuario"));
                        movimiento.setMesaPedido(rs.getString("MesaPedido"));
                        movimiento.setTipoOperacion(rs.getString("TipoOperacion"));
                        movimiento.setTotalRegistros(rs.getInt("TotalRegistros"));
                        return movimiento;
                    }
            );

            Integer totalRegistros = movimientos.isEmpty() ? 0 : movimientos.get(0).getTotalRegistros();
            return new PaginatedResponse<>(movimientos, totalRegistros, pageNumber, pageSize);

        } catch (Exception e) {
            return PaginatedResponse.error("Error al obtener movimientos de inventario: " + e.getMessage());
        }
    }

    @Override
    public PaginatedResponse<MovimientoCaja> obtenerMovimientosCaja(
            LocalDate fechaDesde, LocalDate fechaHasta, Integer tipoMovimientoId,
            String usuario, Integer sesionId, String tipoPago, Integer pageNumber, Integer pageSize) {

        try {
            List<MovimientoCaja> movimientos = jdbcTemplate.query(
                    StoredProcedureR.SP_OBTENER_MOVIMIENTOS_CAJA,
                    new Object[]{
                            fechaDesde != null ? Date.valueOf(fechaDesde) : null,
                            fechaHasta != null ? Date.valueOf(fechaHasta) : null,
                            tipoMovimientoId,
                            usuario,
                            sesionId,
                            tipoPago,
                            pageNumber,
                            pageSize
                    },
                    (rs, rowNum) -> {
                        MovimientoCaja movimiento = new MovimientoCaja();
                        movimiento.setMovimientoId(rs.getInt("MovimientoId"));
                        movimiento.setFechaMovimiento(rs.getTimestamp("FechaMovimiento").toLocalDateTime());
                        movimiento.setSesionId(rs.getInt("SesionId"));
                        movimiento.setUsuarioSesion(rs.getString("UsuarioSesion"));
                        movimiento.setTipoMovimiento(rs.getString("TipoMovimiento"));
                        movimiento.setEsIngreso(rs.getBoolean("EsIngreso"));
                        movimiento.setDescripcion(rs.getString("Descripcion"));
                        movimiento.setMonto(rs.getBigDecimal("Monto"));
                        movimiento.setTipoPago(rs.getString("TipoPago"));
                        movimiento.setPedidoId(rs.getObject("PedidoId") != null ? rs.getInt("PedidoId") : null);
                        movimiento.setComprobanteId(rs.getObject("ComprobanteId") != null ? rs.getInt("ComprobanteId") : null);
                        movimiento.setUsuario(rs.getString("Usuario"));
                        movimiento.setObservaciones(rs.getString("Observaciones"));
                        movimiento.setMesaPedido(rs.getString("MesaPedido"));
                        movimiento.setNumeroComprobante(rs.getString("NumeroComprobante"));
                        movimiento.setTipoOperacion(rs.getString("TipoOperacion"));
                        movimiento.setTotalRegistros(rs.getInt("TotalRegistros"));
                        return movimiento;
                    }
            );

            Integer totalRegistros = movimientos.isEmpty() ? 0 : movimientos.get(0).getTotalRegistros();
            return new PaginatedResponse<>(movimientos, totalRegistros, pageNumber, pageSize);

        } catch (Exception e) {
            return PaginatedResponse.error("Error al obtener movimientos de caja: " + e.getMessage());
        }
    }

    @Override
    public PaginatedResponse<MovimientoConsolidado> obtenerMovimientosConsolidados(
            LocalDate fechaDesde, LocalDate fechaHasta, String usuario,
            String tipoMovimiento, Integer pageNumber, Integer pageSize) {

        try {
            List<MovimientoConsolidado> movimientos = jdbcTemplate.query(
                    StoredProcedureR.SP_OBTENER_MOVIMIENTOS_CONSOLIDADOS,
                    new Object[]{
                            fechaDesde != null ? Date.valueOf(fechaDesde) : null,
                            fechaHasta != null ? Date.valueOf(fechaHasta) : null,
                            usuario,
                            tipoMovimiento,
                            pageNumber,
                            pageSize
                    },
                    (rs, rowNum) -> {
                        MovimientoConsolidado movimiento = new MovimientoConsolidado();
                        movimiento.setTipoSistema(rs.getString("TipoSistema"));
                        movimiento.setFechaMovimiento(rs.getTimestamp("FechaMovimiento").toLocalDateTime());
                        movimiento.setUsuario(rs.getString("Usuario"));
                        movimiento.setDescripcion(rs.getString("Descripcion"));
                        movimiento.setDetalle(rs.getString("Detalle"));
                        movimiento.setMontoAfectado(rs.getBigDecimal("MontoAfectado"));
                        movimiento.setTipoOperacion(rs.getString("TipoOperacion"));
                        movimiento.setReferencia(rs.getString("Referencia"));
                        movimiento.setTotalRegistros(rs.getInt("TotalRegistros"));
                        return movimiento;
                    }
            );

            Integer totalRegistros = movimientos.isEmpty() ? 0 : movimientos.get(0).getTotalRegistros();
            return new PaginatedResponse<>(movimientos, totalRegistros, pageNumber, pageSize);

        } catch (Exception e) {
            return PaginatedResponse.error("Error al obtener movimientos consolidados: " + e.getMessage());
        }
    }

    @Override
    public EstadisticasMovimientos obtenerEstadisticas(LocalDate fechaDesde, LocalDate fechaHasta) {
        try {
            List<EstadisticasMovimientos> estadisticas = jdbcTemplate.query(
                    StoredProcedureR.SP_OBTENER_ESTADISTICAS,
                    new Object[]{
                            fechaDesde != null ? Date.valueOf(fechaDesde) : null,
                            fechaHasta != null ? Date.valueOf(fechaHasta) : null
                    },
                    (rs, rowNum) -> {
                        EstadisticasMovimientos stats = new EstadisticasMovimientos();
                        stats.setTotalMovimientosInventario(rs.getInt("TotalMovimientosInventario"));
                        stats.setTotalEntradasInventario(rs.getInt("TotalEntradasInventario"));
                        stats.setTotalSalidasInventario(rs.getInt("TotalSalidasInventario"));
                        stats.setTotalMovimientosCaja(rs.getInt("TotalMovimientosCaja"));
                        stats.setTotalIngresosCaja(rs.getBigDecimal("TotalIngresosCaja"));
                        stats.setTotalEgresosCaja(rs.getBigDecimal("TotalEgresosCaja"));
                        stats.setTotalVentas(rs.getInt("TotalVentas"));
                        stats.setBalanceNeto(rs.getBigDecimal("BalanceNeto"));
                        return stats;
                    }
            );

            return estadisticas.isEmpty() ? new EstadisticasMovimientos() : estadisticas.get(0);

        } catch (Exception e) {
            return new EstadisticasMovimientos();
        }
    }

    @Override
    public List<TipoMovimiento> obtenerTiposMovimiento() {
        return jdbcTemplate.query(
                StoredProcedureR.SP_OBTENER_TIPOS_MOVIMIENTO,
                (rs, rowNum) -> {
                    TipoMovimiento tipo = new TipoMovimiento();
                    tipo.setTipoSistema(rs.getString("TipoSistema"));
                    tipo.setTipoId(rs.getInt("TipoId"));
                    tipo.setDescripcion(rs.getString("Descripcion"));
                    tipo.setActivo(rs.getBoolean("Activo"));
                    return tipo;
                }
        );
    }

    @Override
    public List<UsuarioMovimiento> obtenerUsuariosMovimientos() {
        return jdbcTemplate.query(
                StoredProcedureR.SP_OBTENER_USUARIOS_MOVIMIENTOS,
                (rs, rowNum) -> {
                    UsuarioMovimiento usuario = new UsuarioMovimiento();
                    usuario.setUsuario(rs.getString("Usuario"));
                    return usuario;
                }
        );
    }
}