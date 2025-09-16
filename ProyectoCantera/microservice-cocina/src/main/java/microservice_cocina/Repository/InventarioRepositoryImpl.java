package microservice_cocina.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InventarioRepositoryImpl implements InventarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> procesarSalidaInventarioPedido(int pedidoId, Integer usuarioId) {
        String sql = "EXEC sp_procesar_salida_inventario_pedido ?, ?";

        List<Map<String, Object>> resultado = jdbcTemplate.query(sql,
                new Object[]{pedidoId, usuarioId}, (rs, rowNum) -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", rs.getBoolean("Success"));
                    response.put("mensaje", rs.getString("Mensaje"));
                    return response;
                });

        return resultado.isEmpty() ?
                Map.of("success", false, "mensaje", "Error al procesar inventario") :
                resultado.get(0);
    }

    @Override
    public List<Map<String, Object>> obtenerStockInsumos() {
        String sql = "EXEC sp_obtener_stock_insumos";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, Object> insumo = new HashMap<>();
            insumo.put("insumoId", rs.getInt("nInsumoId"));
            insumo.put("nombreInsumo", rs.getString("cNombreInsumo"));
            insumo.put("unidadMedida", rs.getString("cUnidadMedida"));
            insumo.put("stockActual", rs.getBigDecimal("nStockActual"));
            insumo.put("categoria", rs.getString("Categoria"));
            insumo.put("estadoStock", rs.getString("EstadoStock"));
            return insumo;
        });
    }

    @Override
    public List<Map<String, Object>> obtenerReporteMovimientos(String fechaDesde, String fechaHasta, Integer insumoId, Integer tipoMovimientoId) {
        String sql = "EXEC sp_obtener_reporte_movimientos_inventario ?, ?, ?, ?";

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
                new Object[]{sqlFechaDesde, sqlFechaHasta, insumoId, tipoMovimientoId},
                (rs, rowNum) -> {
                    Map<String, Object> movimiento = new HashMap<>();
                    movimiento.put("movimientoId", rs.getInt("nMovimientoInventarioId"));
                    movimiento.put("fechaMovimiento", rs.getTimestamp("dFechaMovimiento"));
                    movimiento.put("tipoMovimiento", rs.getString("TipoMovimiento"));
                    movimiento.put("nombreInsumo", rs.getString("cNombreInsumo"));
                    movimiento.put("unidadMedida", rs.getString("cUnidadMedida"));
                    movimiento.put("cantidadMovimiento", rs.getBigDecimal("nCantidadMovimiento"));
                    movimiento.put("stockAnterior", rs.getBigDecimal("nStockAnterior"));
                    movimiento.put("stockNuevo", rs.getBigDecimal("nStockNuevo"));
                    movimiento.put("observaciones", rs.getString("cObservaciones"));
                    movimiento.put("pedidoId", rs.getObject("nPedidoID"));
                    movimiento.put("tipoOperacion", rs.getString("TipoOperacion"));
                    return movimiento;
                });
    }
}