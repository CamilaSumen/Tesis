package microservice_delivery.Service;

import microservice_delivery.Model.Delivery;

import java.util.List;

public interface DeliveryService {

    /*SERVICES DE LAS DELIVERY*/
    List<Delivery> listarDeliverys();
    void insertarDelivery(Delivery delivery);
    void eliminarDeliveryLogico(int id);
    void modificarDelivery(Delivery delivery);

    
}
