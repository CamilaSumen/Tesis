package microservice_categoriainsumo.Controller;

import microservice_categoriainsumo.Model.Categoriainsumo;
import microservice_categoriainsumo.Service.CategoriainsumoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoriainsumo")
public class CategoriainsumoController {

    private final CategoriainsumoService categoriainsumoService;

    public CategoriainsumoController(CategoriainsumoService categoriainsumoService) {
        this.categoriainsumoService = categoriainsumoService;
    }

    @GetMapping("/listar")
    public List<Categoriainsumo> listar() {
        return categoriainsumoService.listarCategoriainsumos();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Categoriainsumo categoriainsumo) {
        categoriainsumoService.insertarCategoriainsumo(categoriainsumo);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Categoriainsumo categoriainsumo) {
        categoriainsumoService.modificarCategoriainsumo(categoriainsumo);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        categoriainsumoService.eliminarCategoriainsumoLogico(id);
    }

}
