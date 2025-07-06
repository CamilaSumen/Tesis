package microservice.turno.Controller;

import microservice.turno.Model.Turno;
import microservice.turno.Service.TurnoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turno")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class TurnoController {

    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping("/listar")
    public List<Turno> listar() {
        return turnoService.listarTurnos();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Turno turno) {
        turnoService.insertarTurno(turno);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Turno turno) {
        turnoService.modificarTurno(turno);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        turnoService.eliminarTurnoLogico(id);
    }
}