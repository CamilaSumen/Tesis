package microservice_proveedor.Controller;

import microservice_proveedor.Model.Proveedor;
import microservice_proveedor.Service.ProveedorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @GetMapping("/listar")
    public List<Proveedor> listar() {
        return proveedorService.listarProveedors();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Proveedor proveedor) {
        proveedorService.insertarProveedor(proveedor);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Proveedor proveedor) {
        proveedorService.modificarProveedor(proveedor);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        proveedorService.eliminarProveedorLogico(id);
    }
    
}
