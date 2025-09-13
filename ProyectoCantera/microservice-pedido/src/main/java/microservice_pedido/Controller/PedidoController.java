package microservice_pedido.Controller;
import com.microservice.mesa.microservice_mesa.Model.Pedido;
import microservice_pedido.ModelDTO.PedidoActualizarRequest;
import microservice_pedido.ModelDTO.PedidoRequest;
import microservice_pedido.Response.PedidoResponse;
import microservice_pedido.Service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/listar")
    public List<Pedido> listar() {
        return pedidoService.listarPedidos();
    }

    @PostMapping("/guardar")
    public PedidoResponse guardarPedido(@RequestBody PedidoRequest pedidoRequest) {  // ← Cambio aquí
        return pedidoService.guardarPedido(pedidoRequest);
    }

    @GetMapping("/obtener/{id}")
    public Pedido obtenerPorId(@PathVariable int id) {
        return pedidoService.obtenerPedidoPorId(id);
    }

    @PutMapping("/estado")
    public void modificarEstado(@RequestBody Map<String, Object> request) {
        int pedidoId = (Integer) request.get("pedidoId");
        String estado = (String) request.get("estado");
        pedidoService.modificarEstadoPedido(pedidoId, estado);
    }

    @GetMapping("/obtener-para-editar/{pedidoId}")
    public List<Map<String, Object>> obtenerPedidoParaEditar(@PathVariable int pedidoId) {
        return pedidoService.obtenerPedidoParaEditar(pedidoId);
    }

    @PutMapping("/actualizar")
    public PedidoResponse actualizarPedido(@RequestBody PedidoActualizarRequest request) {
        return pedidoService.actualizarPedido(request);
    }
}
