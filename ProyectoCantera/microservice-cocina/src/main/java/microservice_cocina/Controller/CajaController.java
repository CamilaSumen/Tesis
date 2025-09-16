package microservice_cocina.Controller;

import microservice_cocina.Service.CajaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/caja")
public class CajaController {

    private final CajaService cajaService;

    public CajaController(CajaService cajaService) {
        this.cajaService = cajaService;
    }

    @PostMapping("/registrar-venta")
    public Map<String, Object> registrarVentaCaja(@RequestBody Map<String, Object> request) {
        int pedidoId = (Integer) request.get("pedidoId");
        Integer comprobanteId = (Integer) request.get("comprobanteId");
        String tipoPago = (String) request.get("tipoPago");

        // Manejo seguro de números que pueden venir como Integer o Double
        Double montoTotal = convertToDouble(request.get("montoTotal"));
        Double montoPropina = convertToDouble(request.getOrDefault("montoPropina", 0));
        Double montoDescuento = convertToDouble(request.getOrDefault("montoDescuento", 0));
        String usuario = (String) request.get("usuario");

        return cajaService.registrarVentaCaja(pedidoId, comprobanteId, tipoPago,
                montoTotal, montoPropina, montoDescuento, usuario);
    }


    @PostMapping("/abrir-sesion")
    public Map<String, Object> abrirSesionCaja(@RequestBody Map<String, Object> request) {
        String usuario = (String) request.get("usuario");
        Double montoApertura = convertToDouble(request.get("montoApertura"));
        String observaciones = (String) request.getOrDefault("observaciones", null);

        return cajaService.abrirSesionCaja(usuario, montoApertura, observaciones);
    }

    @PostMapping("/cerrar-sesion")
    public Map<String, Object> cerrarSesionCaja(@RequestBody Map<String, Object> request) {
        String usuario = (String) request.get("usuario");
        Double montoCierre = convertToDouble(request.get("montoCierre"));
        String observaciones = (String) request.getOrDefault("observaciones", null);

        return cajaService.cerrarSesionCaja(usuario, montoCierre, observaciones);
    }


    private Double convertToDouble(Object value) {
        if (value == null) return 0.0;
        if (value instanceof Double) return (Double) value;
        if (value instanceof Integer) return ((Integer) value).doubleValue();
        if (value instanceof Number) return ((Number) value).doubleValue();
        try {
            return Double.parseDouble(value.toString());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    @GetMapping("/resumen-actual/{usuario}")
    public Map<String, Object> obtenerResumenCajaActual(@PathVariable String usuario) {
        return cajaService.obtenerResumenCajaActual(usuario);
    }


    @GetMapping("/reporte")
    public List<Map<String, Object>> obtenerReporteCaja(
            @RequestParam(required = false) Integer sesionId,
            @RequestParam(required = false) String usuario,
            @RequestParam(required = false) String fechaDesde,
            @RequestParam(required = false) String fechaHasta) {

        return cajaService.obtenerReporteCaja(sesionId, usuario, fechaDesde, fechaHasta);
    }
}
