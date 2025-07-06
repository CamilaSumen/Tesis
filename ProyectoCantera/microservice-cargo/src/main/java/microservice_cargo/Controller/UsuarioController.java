package microservice_cargo.Controller;
import microservice_cargo.Model.Usuario;
import microservice_cargo.Service.CargoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rrhh")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UsuarioController {

    private final CargoService cargoService;

    public UsuarioController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("/usuario/listar")
    public List<Usuario> listar() {
        return cargoService.listarUsuarios();
    }

    @PostMapping("/usuario/insertar")
    public void insertar(@RequestBody Usuario usuario) {
        cargoService.insertarUsuario(usuario);
    }

    @PutMapping("/usuario/modificar")
    public void modificar(@RequestBody Usuario usuario) {
        cargoService.modificarUsuario(usuario);
    }

    @DeleteMapping("/usuario/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        cargoService.eliminarUsuarioLogico(id);
    }
}