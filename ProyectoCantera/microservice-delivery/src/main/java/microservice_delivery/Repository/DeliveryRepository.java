package microservice_delivery.Repository;

import microservice_delivery.Model.Delivery;

import java.util.List;

public interface DeliveryRepository {

    List<Delivery> listarDeliverys();
    void insertarDelivery(Delivery delivery);
    void eliminarDeliveryLogico(int id);
    void modificarDelivery(Delivery delivery);

}
