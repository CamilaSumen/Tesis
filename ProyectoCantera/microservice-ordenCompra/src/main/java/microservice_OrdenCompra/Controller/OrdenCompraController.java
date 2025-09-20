package microservice_OrdenCompra.Controller;


import microservice_OrdenCompra.Model.*;
import microservice_OrdenCompra.Service.OrdenCompraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ordenCompra")
public class OrdenCompraController {

    private final OrdenCompraService ordenCompraService;

    public OrdenCompraController(OrdenCompraService ordenCompraService) {
        this.ordenCompraService = ordenCompraService;
    }

    @PostMapping("/crear")
    public OrdenCompraResponse crearOrdenCompra(@RequestBody OrdenCompraRequest request) {
        return ordenCompraService.crearOrdenCompra(request);
    }

    @PostMapping("/recibir")
    public Map<String, Object> recibirOrdenCompra(@RequestBody Map<String, Object> request) {
        Integer ordenCompraId = (Integer) request.get("ordenCompraId");
        String usuario = (String) request.get("usuario");
        String observaciones = (String) request.getOrDefault("observaciones", null);

        return ordenCompraService.recibirOrdenCompra(ordenCompraId, usuario, observaciones);
    }

    @PostMapping("/registrar-pago")
    public Map<String, Object> registrarPagoOrden(@RequestBody PagoOrdenCompraRequest request) {
        return ordenCompraService.registrarPagoOrden(request);
    }

    @PostMapping("/anular")
    public Map<String, Object> anularOrdenCompra(@RequestBody Map<String, Object> request) {
        Integer ordenCompraId = (Integer) request.get("ordenCompraId");
        String usuario = (String) request.get("usuario");
        String motivoAnulacion = (String) request.get("motivoAnulacion");

        return ordenCompraService.anularOrdenCompra(ordenCompraId, usuario, motivoAnulacion);
    }

    @GetMapping("/listar")
    public List<OrdenCompra> listarOrdenesCompra(
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin,
            @RequestParam(required = false) Integer proveedorId,
            @RequestParam(required = false) Integer estadoId) {

        return ordenCompraService.listarOrdenesCompra(fechaInicio, fechaFin, proveedorId, estadoId);
    }

    @GetMapping("/{ordenCompraId}")
    public OrdenCompra obtenerDetalleOrden(@PathVariable Integer ordenCompraId) {
        return ordenCompraService.obtenerDetalleOrden(ordenCompraId);
    }

    @GetMapping("/{ordenCompraId}/detalle")
    public List<DetalleOrdenCompra> obtenerDetallesOrden(@PathVariable Integer ordenCompraId) {
        return ordenCompraService.obtenerDetallesOrden(ordenCompraId);
    }


    @GetMapping("/{ordenCompraId}/comprobante")
    public ComprobanteCompra obtenerComprobanteCompra(@PathVariable Integer ordenCompraId) {
        return ordenCompraService.obtenerComprobanteCompra(ordenCompraId);
    }
}