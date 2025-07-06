package microservice.privilegio.microservice_privilegio.Controller;

import microservice.privilegio.microservice_privilegio.Model.Privilegio;
import microservice.privilegio.microservice_privilegio.Service.PrivilegioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/privilegio")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class PrivilegioController {

    private final PrivilegioService privilegioService;

    public PrivilegioController(PrivilegioService privilegioService) {
        this.privilegioService = privilegioService;
    }

    @GetMapping("/listar")
    public List<Privilegio> listar() {
        return privilegioService.listarPrivilegios();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Privilegio privilegio) {
        privilegioService.insertarPrivilegio(privilegio);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Privilegio privilegio) {
        privilegioService.modificarPrivilegio(privilegio);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        privilegioService.eliminarPrivilegioLogico(id);
    }

}