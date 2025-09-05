package microservice_delivery.Controller;

import microservice_delivery.Model.Delivery;
import microservice_delivery.Service.DeliveryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/listar")
    public List<Delivery> listar() {
        return deliveryService.listarDeliverys();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody Delivery delivery) {
        deliveryService.insertarDelivery(delivery);
    }

    @PutMapping("/modificar")
    public void modificar(@RequestBody Delivery delivery) {
        deliveryService.modificarDelivery(delivery);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable int id) {
        deliveryService.eliminarDeliveryLogico(id);
    }

}

