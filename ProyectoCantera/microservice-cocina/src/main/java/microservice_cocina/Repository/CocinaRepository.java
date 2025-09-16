package microservice_cocina.Repository;

import java.util.List;
import java.util.Map;

public interface CocinaRepository {

    List<Map<String, Object>> obtenerPedidosPorEstado(String estado);
    Map<String, Object> cambiarEstadoPedido(int pedidoId, String nuevoEstado);
    Map<String, Object> obtenerDetallePedidoCocina(int pedidoId);
    Map<String, Object> obtenerEstadisticasCocina();
}
