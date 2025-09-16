package microservice_cocina.Service;
import java.util.List;
import java.util.Map;

public interface InventarioService {
    Map<String, Object> procesarSalidaInventarioPedido(int pedidoId, Integer usuarioId);
    List<Map<String, Object>> obtenerStockInsumos();
    List<Map<String, Object>> obtenerReporteMovimientos(String fechaDesde, String fechaHasta, Integer insumoId, Integer tipoMovimientoId);
}
