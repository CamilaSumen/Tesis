package microservice_cocina.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CajaRepositoryImpl implements CajaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> registrarVentaCaja(int pedidoId, Integer comprobanteId, String tipoPago,
                                                  Double montoTotal, Double montoPropina, Double montoDescuento, String usuario) {
        String sql = "EXEC sp_registrar_venta_caja ?, ?, ?, ?, ?, ?, ?";

        List<Map<String, Object>> resultado = jdbcTemplate.query(sql,
                new Object[]{pedidoId, comprobanteId, tipoPago,
                        BigDecimal.valueOf(montoTotal),
                        BigDecimal.valueOf(montoPropina),
                        BigDecimal.valueOf(montoDescuento),
                        usuario},
                (rs, rowNum) -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", rs.getBoolean("Success"));
                    response.put("mensaje", rs.getString("Mensaje"));
                    return response;
                });

        return resultado.isEmpty() ?
                Map.of("success", false, "mensaje", "Error al registrar venta en caja") :
                resultado.get(0);
    }

    @Override
    public Map<String, Object> abrirSesionCaja(String usuario, Double montoApertura, String observaciones) {
        String sql = "EXEC sp_abrir_sesion_caja ?, ?, ?";

        List<Map<String, Object>> resultado = jdbcTemplate.query(sql,
                new Object[]{usuario, BigDecimal.valueOf(montoApertura), observaciones},
                (rs, rowNum) -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", rs.getBoolean("Success"));
                    response.put("mensaje", rs.getString("Mensaje"));
                    try {
                        response.put("sesionId", rs.getInt("SesionId"));
                    } catch (Exception e) {
                        // SesionId puede no estar presente si hay error
                    }
                    return response;
                });

        return resultado.isEmpty() ?
                Map.of("success", false, "mensaje", "Error al abrir sesión de caja") :
                resultado.get(0);
    }

    @Override
    public Map<String, Object> obtenerResumenCajaActual(String usuario) {
        String sql = "EXEC sp_obtener_resumen_caja_actual ?";

        List<Map<String, Object>> resultado = jdbcTemplate.query(sql,
                new Object[]{usuario},
                (rs, rowNum) -> {
                    Map<String, Object> resumen = new HashMap<>();
                    resumen.put("tieneSesionActiva", rs.getBoolean("TieneSesionActiva"));

                    if (rs.getBoolean("TieneSesionActiva")) {
                        resumen.put("sesionId", rs.getInt("nSesionCajaId"));
                        resumen.put("usuario", rs.getString("cUsuario"));
                        resumen.put("fechaApertura", rs.getTimestamp("dFechaApertura"));
                        resumen.put("montoApertura", rs.getBigDecimal("nMontoApertura"));
                        resumen.put("totalIngresos", rs.getBigDecimal("TotalIngresos"));
                        resumen.put("totalEgresos", rs.getBigDecimal("TotalEgresos"));
                        resumen.put("montoActualCaja", rs.getBigDecimal("MontoActualCaja"));
                        resumen.put("totalVentas", rs.getInt("TotalVentas"));
                    } else {
                        resumen.put("mensaje", rs.getString("Mensaje"));
                    }

                    return resumen;
                });

        return resultado.isEmpty() ?
                Map.of("tieneSesionActiva", false, "mensaje", "Error al obtener resumen") :
                resultado.get(0);
    }

    @Override
    public List<Map<String, Object>> obtenerReporteCaja(Integer sesionId, String usuario, String fechaDesde, String fechaHasta) {
        String sql = "EXEC sp_obtener_reporte_caja ?, ?, ?, ?";

        Date sqlFechaDesde = null;
        Date sqlFechaHasta = null;

        try {
            if (fechaDesde != null && !fechaDesde.isEmpty()) {
                sqlFechaDesde = Date.valueOf(fechaDesde);
            }
            if (fechaHasta != null && !fechaHasta.isEmpty()) {
                sqlFechaHasta = Date.valueOf(fechaHasta);
            }
        } catch (IllegalArgumentException e) {
        }

        return jdbcTemplate.query(sql,
                new Object[]{sesionId, usuario, sqlFechaDesde, sqlFechaHasta},
                (rs, rowNum) -> {
                    Map<String, Object> movimiento = new HashMap<>();
                    movimiento.put("movimientoId", rs.getInt("nMovimientoCajaId"));
                    movimiento.put("sesionId", rs.getInt("nSesionCajaId"));
                    movimiento.put("usuario", rs.getString("cUsuario"));
                    movimiento.put("fechaMovimiento", rs.getTimestamp("dFechaMovimiento"));
                    movimiento.put("tipoMovimiento", rs.getString("TipoMovimiento"));
                    movimiento.put("esIngreso", rs.getBoolean("bEsIngreso"));
                    movimiento.put("descripcion", rs.getString("cDescripcion"));
                    movimiento.put("monto", rs.getBigDecimal("nMonto"));
                    movimiento.put("tipoPago", rs.getString("cTipoPago"));
                    movimiento.put("pedidoId", rs.getObject("nPedidoID"));
                    movimiento.put("comprobanteId", rs.getObject("nComprobanteID"));
                    movimiento.put("observaciones", rs.getString("cObservaciones"));
                    movimiento.put("tipoOperacion", rs.getString("TipoOperacion"));
                    return movimiento;
                });
    }

    @Override
    public Map<String, Object> cerrarSesionCaja(String usuario, Double montoCierre, String observaciones) {
        Map<String, Object> resumenActual = obtenerResumenCajaActual(usuario);

        if (!(Boolean) resumenActual.get("tieneSesionActiva")) {
            return Map.of("success", false, "mensaje", "No hay sesión activa para cerrar");
        }

        Integer sesionId = (Integer) resumenActual.get("sesionId");

        String sqlUpdate = "UPDATE SesionCaja SET " +
                "dFechaCierre = GETDATE(), " +
                "nMontoCierre = ?, " +
                "nTotalVentas = ?, " +
                "nTotalGastos = ?, " +
                "cEstado = 'Cerrada', " +
                "cObservaciones = ? " +
                "WHERE nSesionCajaId = ?";

        try {
            BigDecimal totalIngresos = (BigDecimal) resumenActual.get("totalIngresos");
            BigDecimal totalEgresos = (BigDecimal) resumenActual.get("totalEgresos");

            int filasActualizadas = jdbcTemplate.update(sqlUpdate,
                    BigDecimal.valueOf(montoCierre),
                    totalIngresos != null ? totalIngresos : BigDecimal.ZERO,
                    totalEgresos != null ? totalEgresos : BigDecimal.ZERO,
                    observaciones,
                    sesionId
            );

            if (filasActualizadas > 0) {
                return Map.of("success", true, "mensaje", "Sesión de caja cerrada correctamente");
            } else {
                return Map.of("success", false, "mensaje", "No se pudo cerrar la sesión");
            }

        } catch (Exception e) {
            return Map.of("success", false, "mensaje", "Error al cerrar sesión: " + e.getMessage());
        }
    }
}