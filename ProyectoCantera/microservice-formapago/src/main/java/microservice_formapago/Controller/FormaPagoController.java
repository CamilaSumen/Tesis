package microservice_formapago.Controller;

import microservice_formapago.Model.FormaPago;
import microservice_formapago.Service.FormaPagoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formapago")
public class FormaPagoController {
    private final FormaPagoService formaPagoService;

    public FormaPagoController(FormaPagoService formaPagoService) {
        this.formaPagoService = formaPagoService;
    }

    @GetMapping("/listar")
    public List<FormaPago> listar() {
        return formaPagoService.listarFormaPagos();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody FormaPago formaPago) {
        formaPagoService.insertarFormaPago(formaPago);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody FormaPago formaPago) {
        formaPagoService.modificarFormaPago(formaPago);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        formaPagoService.eliminarFormaPagoLogico(id);
    }

}
