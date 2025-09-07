package microservice_insumo.Controller;

import microservice_insumo.Model.Insumo;
import microservice_insumo.Service.InsumoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insumo")
public class InsumoController {


    private final InsumoService insumoService;

    public InsumoController(InsumoService insumoService) {
        this.insumoService = insumoService;
    }

    @GetMapping("/listar")
    public List<Insumo> listar() {
        return insumoService.listarInsumos();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Insumo insumo) {
        insumoService.insertarInsumo(insumo);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Insumo insumo) {
        insumoService.modificarInsumo(insumo);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        insumoService.eliminarInsumoLogico(id);
    }
    
}
