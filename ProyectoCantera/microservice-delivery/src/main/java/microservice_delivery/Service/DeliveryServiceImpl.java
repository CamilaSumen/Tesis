package microservice_delivery.Service;

import microservice_delivery.Model.Delivery;
import microservice_delivery.Repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {


    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public List<Delivery> listarDeliverys() {
        return deliveryRepository.listarDeliverys();
    }

    @Override
    public void insertarDelivery(Delivery delivery) {
        deliveryRepository.insertarDelivery(delivery);
    }

    @Override
    public void eliminarDeliveryLogico(int id) {
        deliveryRepository.eliminarDeliveryLogico(id);
    }

    @Override
    public void modificarDelivery(Delivery delivery) {
        deliveryRepository.modificarDelivery(delivery);
    }

}
