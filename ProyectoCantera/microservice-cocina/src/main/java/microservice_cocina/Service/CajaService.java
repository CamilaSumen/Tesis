package microservice_cocina.Service;

import java.util.List;
import java.util.Map;

public interface CajaService {
    Map<String, Object> registrarVentaCaja(int pedidoId, Integer comprobanteId, String tipoPago,
                                           Double montoTotal, Double montoPropina, Double montoDescuento, String usuario);
    Map<String, Object> abrirSesionCaja(String usuario, Double montoApertura, String observaciones);
    Map<String, Object> obtenerResumenCajaActual(String usuario);
    List<Map<String, Object>> obtenerReporteCaja(Integer sesionId, String usuario, String fechaDesde, String fechaHasta);
    Map<String, Object> cerrarSesionCaja(String usuario, Double montoCierre, String observaciones);
}
