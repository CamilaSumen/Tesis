package microservice_pedido.Service;

import com.microservice.mesa.microservice_mesa.Model.Pedido;
import microservice_pedido.ModelDTO.PedidoActualizarRequest;
import microservice_pedido.ModelDTO.PedidoRequest;
import microservice_pedido.Repository.PedidoRepository;
import microservice_pedido.Response.PedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.listarPedidos();
    }

    @Override
    public PedidoResponse guardarPedido(PedidoRequest pedidoRequest) {  // ← Cambio aquí
        return pedidoRepository.guardarPedido(pedidoRequest);
    }

    @Override
    public void modificarEstadoPedido(int pedidoId, String estado) {
        pedidoRepository.modificarEstadoPedido(pedidoId, estado);
    }

    @Override
    public Pedido obtenerPedidoPorId(int pedidoId) {
        return pedidoRepository.obtenerPedidoPorId(pedidoId);
    }

    @Override
    public List<Map<String, Object>> obtenerPedidoParaEditar(int pedidoId) {
        return pedidoRepository.obtenerPedidoParaEditar(pedidoId);
    }

    @Override
    public PedidoResponse actualizarPedido(PedidoActualizarRequest request) {
        return pedidoRepository.actualizarPedido(request);
    }
}