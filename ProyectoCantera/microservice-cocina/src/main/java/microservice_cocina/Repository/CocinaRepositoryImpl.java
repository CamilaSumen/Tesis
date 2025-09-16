package microservice_cocina.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CocinaRepositoryImpl implements CocinaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> obtenerPedidosPorEstado(String estado) {
        String sql = "EXEC sp_obtener_pedidos_cocina_por_estado ?";

        return jdbcTemplate.query(sql, new Object[]{estado}, (rs, rowNum) -> {
            Map<String, Object> pedido = new HashMap<>();

            // Datos básicos del pedido
            pedido.put("pedidoID", rs.getInt("PedidoID"));
            pedido.put("numero", rs.getInt("PedidoID")); // Para compatibilidad con frontend
            pedido.put("mesa", rs.getString("Mesa"));
            pedido.put("mozo", rs.getString("Mozo"));
            pedido.put("estado", rs.getString("Estado"));
            pedido.put("observaciones", rs.getString("Observaciones"));
            pedido.put("totalItems", rs.getInt("TotalItems"));

            // Información de tiempo
            LocalDateTime fechaPedido = rs.getTimestamp("FechaPedido").toLocalDateTime();
            pedido.put("fechaPedido", fechaPedido);
            pedido.put("hora", fechaPedido.format(DateTimeFormatter.ofPattern("HH:mm")));

            // Calcular tiempo transcurrido
            long minutosTranscurridos = java.time.Duration.between(fechaPedido, LocalDateTime.now()).toMinutes();
            pedido.put("tiempoTranscurrido", minutosTranscurridos);

            // Tipo de pedido (por ahora solo Mesa, puedes expandir)
            pedido.put("tipo", rs.getString("Mesa").startsWith("Mesa") ? "Mesa" : "Para Llevar");

            return pedido;
        });
    }

    @Override
    public Map<String, Object> cambiarEstadoPedido(int pedidoId, String nuevoEstado) {
        String sql = "EXEC sp_cambiar_estado_pedido_cocina ?, ?";

        List<Map<String, Object>> resultado = jdbcTemplate.query(sql,
                new Object[]{pedidoId, nuevoEstado}, (rs, rowNum) -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", rs.getBoolean("Success"));
                    response.put("mensaje", rs.getString("Mensaje"));
                    response.put("pedidoID", pedidoId);
                    response.put("nuevoEstado", nuevoEstado);
                    return response;
                });

        return resultado.isEmpty() ? Map.of("success", false, "mensaje", "Error al cambiar estado")
                : resultado.get(0);
    }

    @Override
    public Map<String, Object> obtenerDetallePedidoCocina(int pedidoId) {
        String sql = "EXEC sp_obtener_detalle_pedido_cocina ?";

        // Obtener todo en una sola consulta
        List<Map<String, Object>> resultados = jdbcTemplate.query(sql,
                new Object[]{pedidoId}, (rs, rowNum) -> {
                    Map<String, Object> row = new HashMap<>();

                    // Datos del pedido (se repetirán en cada fila)
                    row.put("pedidoID", rs.getInt("PedidoID"));
                    row.put("numero", rs.getInt("PedidoID"));
                    row.put("mesa", rs.getString("Mesa"));
                    row.put("mozo", rs.getString("Mozo"));
                    row.put("numeroPersonas", rs.getInt("NumeroPersonas"));
                    row.put("estado", rs.getString("Estado"));
                    row.put("observaciones", rs.getString("Observaciones"));
                    row.put("total", rs.getBigDecimal("Total"));

                    LocalDateTime fechaPedido = rs.getTimestamp("FechaPedido").toLocalDateTime();
                    row.put("fechaPedido", fechaPedido);
                    row.put("hora", fechaPedido.format(DateTimeFormatter.ofPattern("HH:mm")));
                    row.put("tipo", rs.getString("Mesa").startsWith("Mesa") ? "Mesa" : "Para Llevar");

                    // Datos del item
                    row.put("detalleId", rs.getInt("DetalleID"));
                    row.put("nombreProducto", rs.getString("NombreProducto"));
                    row.put("cantidad", rs.getInt("Cantidad"));
                    row.put("precioUnitario", rs.getBigDecimal("PrecioUnitario"));
                    row.put("subtotal", rs.getBigDecimal("Subtotal"));

                    return row;
                });

        return Map.of("detallePedido", resultados);
    }


    @Override
    public Map<String, Object> obtenerEstadisticasCocina() {
        String sql = "EXEC sp_obtener_estadisticas_cocina";

        List<Map<String, Object>> resultado = jdbcTemplate.query(sql, (rs, rowNum) -> {
            Map<String, Object> stats = new HashMap<>();
            stats.put("pendientes", rs.getInt("Pendientes"));
            stats.put("enPreparacion", rs.getInt("EnPreparacion"));
            stats.put("listos", rs.getInt("Listos"));
            stats.put("totalHoy", rs.getInt("TotalHoy"));
            stats.put("promedioTiempo", rs.getInt("PromedioTiempoMinutos"));
            return stats;
        });

        return resultado.isEmpty() ? new HashMap<>() : resultado.get(0);
    }
}