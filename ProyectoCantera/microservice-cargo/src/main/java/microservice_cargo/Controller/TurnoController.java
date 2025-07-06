package microservice_cargo.Controller;

import microservice_cargo.Model.Turno;
import microservice_cargo.Service.CargoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rrhh")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class TurnoController {

    private final CargoService cargoService;

    public TurnoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("/turno/listar")
    public List<Turno> listar() {
        return cargoService.listarTurnos();
    }

    @PostMapping("/turno/insertar")
    public void insertar(@RequestBody Turno turno) {
        cargoService.insertarTurno(turno);
    }

    @PutMapping("/turno/modificar")
    public void modificar(@RequestBody Turno turno) {
        cargoService.modificarTurno(turno);
    }

    @DeleteMapping("/turno/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        cargoService.eliminarTurnoLogico(id);
    }
}