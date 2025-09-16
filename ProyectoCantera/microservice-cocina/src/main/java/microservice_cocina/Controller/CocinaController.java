package microservice_cocina.Controller;

import microservice_cocina.Model.CambioEstadoRequest;
import microservice_cocina.Service.CocinaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cocina")
public class CocinaController {

    private final CocinaService cocinaService;

    public CocinaController(CocinaService cocinaService) {
        this.cocinaService = cocinaService;
    }

    @GetMapping("/pedidos")
    public Map<String, List<Map<String, Object>>> obtenerPedidosCocina() {
        return cocinaService.obtenerPedidosCocina();
    }

    @GetMapping("/pedidos/{estado}")
    public List<Map<String, Object>> obtenerPedidosPorEstado(@PathVariable String estado) {
        return cocinaService.obtenerPedidosPorEstado(estado);
    }


    @PutMapping("/cambiar-estado")
    public Map<String, Object> cambiarEstadoPedido(@RequestBody CambioEstadoRequest request) {
        return cocinaService.cambiarEstadoPedido(request.getPedidoID(), request.getNuevoEstado());
    }


    @GetMapping("/pedido/{pedidoId}/detalle")
    public Map<String, Object> obtenerDetallePedidoCocina(@PathVariable int pedidoId) {
        return cocinaService.obtenerDetallePedidoCocina(pedidoId);
    }


    @GetMapping("/estadisticas")
    public Map<String, Object> obtenerEstadisticasCocina() {
        return cocinaService.obtenerEstadisticasCocina();
    }
}