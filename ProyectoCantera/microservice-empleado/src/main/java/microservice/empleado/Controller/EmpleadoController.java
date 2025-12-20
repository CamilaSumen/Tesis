package microservice.empleado.Controller;

import microservice.empleado.Model.Cliente;
import microservice.empleado.Model.Empleado;
import microservice.empleado.Service.EmpleadoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleado")
//@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping("/listar")
    public List<Empleado> listar() {
        return empleadoService.listarEmpleados();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Empleado empleado) {
        empleadoService.insertarEmpleado(empleado);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Empleado empleado) {
        empleadoService.modificarEmpleado(empleado);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        empleadoService.eliminarEmpleadoLogico(id);
    }

    @PostMapping("/listarCliente")
    public List<Cliente> listarCliente(@RequestBody Cliente cliente) {
        // Llamada al servicio para listar los clientes por el DNI recibido en el body
        return empleadoService.listarClientes(cliente);
    }

}