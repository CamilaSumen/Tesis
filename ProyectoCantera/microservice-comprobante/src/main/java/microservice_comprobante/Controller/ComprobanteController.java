package microservice_comprobante.Controller;

import microservice_comprobante.Model.Comprobante;
import microservice_comprobante.Model.ComprobanteRequest;
import microservice_comprobante.Model.ComprobanteResponse;
import microservice_comprobante.Service.ComprobanteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comprobante")
public class ComprobanteController {

    private final ComprobanteService comprobanteService;

    public ComprobanteController(ComprobanteService comprobanteService) {
        this.comprobanteService = comprobanteService;
    }

    @PostMapping("/guardar")
    public ComprobanteResponse guardarComprobante(@RequestBody ComprobanteRequest request) {
        return comprobanteService.guardarComprobante(request);
    }

    @GetMapping("/obtener/{id}")
    public Comprobante obtenerComprobante(@PathVariable int id) {
        return comprobanteService.obtenerComprobante(id);
    }

    @GetMapping("/listar")
    public List<Comprobante> listarComprobantes(
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin,
            @RequestParam(required = false) String mozo) {
        return comprobanteService.listarComprobantes(fechaInicio, fechaFin, mozo);
    }
}