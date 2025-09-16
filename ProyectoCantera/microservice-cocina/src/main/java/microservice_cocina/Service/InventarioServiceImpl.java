package microservice_cocina.Service;
import microservice_cocina.Repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public Map<String, Object> procesarSalidaInventarioPedido(int pedidoId, Integer usuarioId) {
        return inventarioRepository.procesarSalidaInventarioPedido(pedidoId, usuarioId);
    }

    @Override
    public List<Map<String, Object>> obtenerStockInsumos() {
        return inventarioRepository.obtenerStockInsumos();
    }

    @Override
    public List<Map<String, Object>> obtenerReporteMovimientos(String fechaDesde, String fechaHasta, Integer insumoId, Integer tipoMovimientoId) {
        return inventarioRepository.obtenerReporteMovimientos(fechaDesde, fechaHasta, insumoId, tipoMovimientoId);
    }
}