package microservice_adicional.Controller;

import microservice_adicional.Model.Adicional;
import microservice_adicional.Service.AdicionalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adicional")
public class AdicionalController {
    
    private final AdicionalService adicionalService;

    public AdicionalController(AdicionalService adicionalService) {
        this.adicionalService = adicionalService;
    }

    @GetMapping("/listar")
    public List<Adicional> listar() {
        return adicionalService.listarAdicionals();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Adicional adicional) {
        adicionalService.insertarAdicional(adicional);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Adicional adicional) {
        adicionalService.modificarAdicional(adicional);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        adicionalService.eliminarAdicionalLogico(id);
    }

}
