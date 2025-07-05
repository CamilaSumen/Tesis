package microservice_cargo.Controller;
import microservice_cargo.Model.Privilegio;
import microservice_cargo.Service.CargoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rrhh")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class PrivilegioController {

    private final CargoService cargoService;

    public PrivilegioController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("/privilegio/listar")
    public List<Privilegio> listar() {
        return cargoService.listarPrivilegios();
    }

    @PostMapping("/privilegio/insertar")
    public void insertar(@RequestBody Privilegio privilegio) {
        cargoService.insertarPrivilegio(privilegio);
    }

    @PutMapping("/privilegio/modificar")
    public void modificar(@RequestBody Privilegio privilegio) {
        cargoService.modificarPrivilegio(privilegio);
    }

    @DeleteMapping("/privilegio/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        cargoService.eliminarPrivilegioLogico(id);
    }

}
