package microservice_cocina.Repository;

import java.util.List;
import java.util.Map;

public interface InventarioRepository {
    Map<String, Object> procesarSalidaInventarioPedido(int pedidoId, Integer usuarioId);
    List<Map<String, Object>> obtenerStockInsumos();
    List<Map<String, Object>> obtenerReporteMovimientos(String fechaDesde, String fechaHasta, Integer insumoId, Integer tipoMovimientoId);
}