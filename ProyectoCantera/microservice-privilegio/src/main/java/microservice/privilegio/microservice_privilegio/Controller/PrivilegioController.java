package microservice.privilegio.microservice_privilegio.Controller;

import microservice.privilegio.microservice_privilegio.Model.AsignacionRequest;
import microservice.privilegio.microservice_privilegio.Model.Elemento;
import microservice.privilegio.microservice_privilegio.Model.ElementoxCargo;
import microservice.privilegio.microservice_privilegio.Service.PrivilegioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/privilegio")
public class PrivilegioController {

    private final PrivilegioService privilegioService;

    public PrivilegioController(PrivilegioService privilegioService) {
        this.privilegioService = privilegioService;
    }

    /**
     * Listar todos los elementos/interfaces del sistema
     */
    @GetMapping("/elementos")
    public ResponseEntity<List<Elemento>> listarElementos() {
        try {
            List<Elemento> elementos = privilegioService.listarElementos();
            return ResponseEntity.ok(elementos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Listar elementos disponibles para un cargo (los que NO tiene asignados)
     */
    @GetMapping("/elementos/disponibles/{cargoId}")
    public ResponseEntity<List<Elemento>> listarElementosDisponibles(@PathVariable int cargoId) {
        try {
            List<Elemento> elementos = privilegioService.listarElementosDisponibles(cargoId);
            return ResponseEntity.ok(elementos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Listar elementos asignados a un cargo (interfaces con acceso)
     */
    @GetMapping("/cargo/{cargoId}")
    public ResponseEntity<List<ElementoxCargo>> listarElementosPorCargo(@PathVariable int cargoId) {
        try {
            List<ElementoxCargo> elementos = privilegioService.listarElementosPorCargo(cargoId);
            return ResponseEntity.ok(elementos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Asignar un elemento a un cargo
     */
    @PostMapping("/asignar")
    public ResponseEntity<Map<String, Object>> asignarElementoACargo(
            @RequestParam int elementoId,
            @RequestParam int cargoId) {
        try {
            privilegioService.asignarElementoACargo(elementoId, cargoId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Elemento asignado exitosamente");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al asignar elemento: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Eliminar un elemento de un cargo por ID de la relación
     */
    @DeleteMapping("/eliminar/{elementoxCargoId}")
    public ResponseEntity<Map<String, Object>> eliminarElementoDeCargo(@PathVariable int elementoxCargoId) {
        try {
            privilegioService.eliminarElementoDeCargo(elementoxCargoId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Elemento eliminado exitosamente");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al eliminar elemento: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Eliminar un elemento de un cargo por elemento y cargo
     */
    @DeleteMapping("/eliminar/elemento/{elementoId}/cargo/{cargoId}")
    public ResponseEntity<Map<String, Object>> eliminarElementoPorElementoCargo(
            @PathVariable int elementoId,
            @PathVariable int cargoId) {
        try {
            privilegioService.eliminarElementoPorElementoCargo(elementoId, cargoId);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Elemento eliminado exitosamente");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al eliminar elemento: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Guardar asignaciones en lote (elimina las anteriores y crea las nuevas)
     * Este endpoint es el más útil para tu interfaz
     */
    @PostMapping("/guardar-cambios")
    public ResponseEntity<Map<String, Object>> guardarCambios(@RequestBody AsignacionRequest request) {
        try {
            privilegioService.guardarAsignacionesBatch(request.getChargeId(), request.getElementIds());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cambios guardados exitosamente");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al guardar cambios: " + e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}