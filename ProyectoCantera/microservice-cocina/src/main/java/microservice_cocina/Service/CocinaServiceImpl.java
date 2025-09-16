package microservice_cocina.Service;

import microservice_cocina.Repository.CocinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CocinaServiceImpl implements CocinaService {

    @Autowired
    private CocinaRepository cocinaRepository;

    @Override
    public Map<String, List<Map<String, Object>>> obtenerPedidosCocina() {
        Map<String, List<Map<String, Object>>> resultado = new HashMap<>();

        resultado.put("Pendiente", cocinaRepository.obtenerPedidosPorEstado("Pendiente"));
        resultado.put("En Preparación", cocinaRepository.obtenerPedidosPorEstado("En Preparación"));
        resultado.put("Listo", cocinaRepository.obtenerPedidosPorEstado("Listo"));

        return resultado;
    }

    @Override
    public List<Map<String, Object>> obtenerPedidosPorEstado(String estado) {
        return cocinaRepository.obtenerPedidosPorEstado(estado);
    }

    @Override
    public Map<String, Object> cambiarEstadoPedido(int pedidoId, String nuevoEstado) {
        return cocinaRepository.cambiarEstadoPedido(pedidoId, nuevoEstado);
    }

    @Override
    public Map<String, Object> obtenerDetallePedidoCocina(int pedidoId) {
        return cocinaRepository.obtenerDetallePedidoCocina(pedidoId);
    }

    @Override
    public Map<String, Object> obtenerEstadisticasCocina() {
        return cocinaRepository.obtenerEstadisticasCocina();
    }
}