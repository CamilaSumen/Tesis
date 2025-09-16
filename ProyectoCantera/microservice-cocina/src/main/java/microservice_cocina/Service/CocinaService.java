package microservice_cocina.Service;

import java.util.List;
import java.util.Map;

public interface CocinaService {

    Map<String, List<Map<String, Object>>> obtenerPedidosCocina();
    List<Map<String, Object>> obtenerPedidosPorEstado(String estado);
    Map<String, Object> cambiarEstadoPedido(int pedidoId, String nuevoEstado);
    Map<String, Object> obtenerDetallePedidoCocina(int pedidoId);
    Map<String, Object> obtenerEstadisticasCocina();

}
