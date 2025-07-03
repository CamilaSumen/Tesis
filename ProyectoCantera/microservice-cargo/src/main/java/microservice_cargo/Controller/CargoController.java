package microservice_cargo.Controller;

import microservice_cargo.Model.Cargo;
import microservice_cargo.Service.CargoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cargo")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class CargoController {

    private final CargoService cargoService;

    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("/listar")
    public List<Cargo> listar() {
        return cargoService.listarCargos();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Cargo cargo) {
        cargoService.insertarCargo(cargo);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Cargo cargo) {
        cargoService.modificarCargo(cargo);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        cargoService.eliminarCargoLogico(id);
    }

}
