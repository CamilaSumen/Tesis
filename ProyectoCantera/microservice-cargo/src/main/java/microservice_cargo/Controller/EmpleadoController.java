package microservice_cargo.Controller;

import microservice_cargo.Model.Empleado;
import microservice_cargo.Service.CargoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/rrhh")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class EmpleadoController {

    private final CargoService cargoService;

    public EmpleadoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping("/empleado/listar")
    public List<Empleado> listar() {
        return cargoService.listarEmpleados();
    }

    @PostMapping("/empleado/insertar")
    public void insertar(@RequestBody Empleado empleado) {
        cargoService.insertarEmpleado(empleado);
    }

    @PutMapping("/empleado/modificar")
    public void modificar(@RequestBody Empleado empleado) {
        cargoService.modificarEmpleado(empleado);
    }

    @DeleteMapping("/empleado/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        cargoService.eliminarEmpleadoLogico(id);
    }
}
