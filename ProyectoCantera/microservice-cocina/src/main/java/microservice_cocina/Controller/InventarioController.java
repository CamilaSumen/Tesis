package microservice_cocina.Controller;

import microservice_cocina.Service.InventarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @PostMapping("/procesar-salida-pedido")
    public Map<String, Object> procesarSalidaInventarioPedido(@RequestBody Map<String, Object> request) {
        int pedidoId = (Integer) request.get("pedidoId");
        Integer usuarioId = null;
        Object usuarioIdObj = request.get("cCodUsuario");

        if (usuarioIdObj != null) {
            if (usuarioIdObj instanceof Integer) {
                usuarioId = (Integer) usuarioIdObj;
            } else if (usuarioIdObj instanceof String) {
                try {
                    usuarioId = Integer.parseInt((String) usuarioIdObj);
                } catch (NumberFormatException e) {
                    usuarioId = null;
                }
            }
        }
        return inventarioService.procesarSalidaInventarioPedido(pedidoId, usuarioId);
    }


    @GetMapping("/stock")
    public List<Map<String, Object>> obtenerStockInsumos() {
        return inventarioService.obtenerStockInsumos();
    }


    @GetMapping("/movimientos")
    public List<Map<String, Object>> obtenerReporteMovimientos(
            @RequestParam(required = false) String fechaDesde,
            @RequestParam(required = false) String fechaHasta,
            @RequestParam(required = false) Integer insumoId,
            @RequestParam(required = false) Integer tipoMovimientoId) {

        return inventarioService.obtenerReporteMovimientos(fechaDesde, fechaHasta, insumoId, tipoMovimientoId);
    }
}
