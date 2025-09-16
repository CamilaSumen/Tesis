package microservice_cocina.Service;
import microservice_cocina.Repository.CajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CajaServiceImpl implements CajaService {

    @Autowired
    private CajaRepository cajaRepository;

    @Override
    public Map<String, Object> registrarVentaCaja(int pedidoId, Integer comprobanteId, String tipoPago,
                                                  Double montoTotal, Double montoPropina, Double montoDescuento, String usuario) {
        return cajaRepository.registrarVentaCaja(pedidoId, comprobanteId, tipoPago,
                montoTotal, montoPropina, montoDescuento, usuario);
    }

    @Override
    public Map<String, Object> abrirSesionCaja(String usuario, Double montoApertura, String observaciones) {
        return cajaRepository.abrirSesionCaja(usuario, montoApertura, observaciones);
    }

    @Override
    public Map<String, Object> obtenerResumenCajaActual(String usuario) {
        return cajaRepository.obtenerResumenCajaActual(usuario);
    }

    @Override
    public List<Map<String, Object>> obtenerReporteCaja(Integer sesionId, String usuario, String fechaDesde, String fechaHasta) {
        return cajaRepository.obtenerReporteCaja(sesionId, usuario, fechaDesde, fechaHasta);
    }

    @Override
    public Map<String, Object> cerrarSesionCaja(String usuario, Double montoCierre, String observaciones) {
        return cajaRepository.cerrarSesionCaja(usuario, montoCierre, observaciones);
    }
}
