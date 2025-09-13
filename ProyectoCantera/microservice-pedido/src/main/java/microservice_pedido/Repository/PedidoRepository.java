package microservice_pedido.Repository;



import com.microservice.mesa.microservice_mesa.Model.Pedido;
import microservice_pedido.ModelDTO.PedidoActualizarRequest;
import microservice_pedido.ModelDTO.PedidoRequest;
import microservice_pedido.Response.PedidoResponse;

import java.util.List;
import java.util.Map;

public interface PedidoRepository {

    List<Pedido> listarPedidos();
    PedidoResponse guardarPedido(PedidoRequest pedidoRequest);  // ← Cambio aquí
    void modificarEstadoPedido(int pedidoId, String estado);
    Pedido obtenerPedidoPorId(int pedidoId);

    List<Map<String, Object>> obtenerPedidoParaEditar(int pedidoId);
    PedidoResponse actualizarPedido(PedidoActualizarRequest request);
}